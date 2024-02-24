<script setup lang="ts">
import { computed, onBeforeUnmount, ref } from 'vue'
import NewGameView from '@/views/game/NewGameView.vue'
import dayjs, { type Dayjs } from 'dayjs'
import { displayErrorMessage, handlingError, loader, trackingLoading, useFetch } from '@/fetch'
import { subscribeComponent } from '@/stomp'

const selectedMonth = ref(dayjs())

const period = computed(() => (
  {
    start: selectedMonth.value.startOf('month'),
    end: selectedMonth.value.endOf('month')
  })
)

const params = computed(() => {
  const urlSearchParams = new URLSearchParams()
  urlSearchParams.set('period.start', period.value.start.format('YYYY-MM-DD'))
  urlSearchParams.set('period.end', period.value.end.format('YYYY-MM-DD'))
  return urlSearchParams
})

type GameView = {
  id: number
  gym: string
  date: string
  entryTime: string
  startTime: string
  owner: string
  guest: string
}

const loading = ref(false)
const load = trackingLoading(
  handlingError(loader<GameView>('/api/game/view', params), displayErrorMessage),
  loading
)
const games = useFetch(load)
subscribeComponent('/game', async () => games.value = await load())

function onPanelChange(month: Dayjs) {
  selectedMonth.value = month
}

function gamesByDate(date: Dayjs): Array<GameView> {
  return games.value?.filter((game: GameView) => dayjs(game.date, 'YYYY-MM-DD').isSame(date, 'date')) ?? []
}

function formatTime(time: string) {
  return dayjs(time, 'HH:mm').format('HH:mm')
}

const open = ref(false)

function openForm() {
  open.value = true
}
</script>

<template>
  <a-card title="График игр" style="height: 100%">
    <a-space direction="vertical" style="width: 100%">
      <a-button type="primary" @click="openForm">Назначить игру</a-button>
      <a-spin :spinning="loading">
        <a-calendar @panel-change="onPanelChange">
          <template #dateCellRender="{current}">
            <div v-for="(game, i) in gamesByDate(current)" :key="i" style="font-size: 12px">
              {{game.gym}}<br>
              Хозяева: {{game.owner}}<br>
              Гости: {{game.guest}}<br>
              Вход: {{formatTime(game.entryTime)}} Свисток: {{formatTime(game.startTime)}}
            </div>
          </template>
        </a-calendar>
      </a-spin>
    </a-space>
    <NewGameView v-model:open="open"/>
  </a-card>
</template>