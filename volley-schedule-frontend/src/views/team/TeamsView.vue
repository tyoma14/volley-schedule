<script setup lang="ts">
import NewTeamView from '@/views/team/NewTeamView.vue'
import type { TableColumnsType } from 'ant-design-vue'
import { displayErrorMessage, handlingError, loader, trackingLoading, useFetch } from '@/fetch'
import { ref } from 'vue'
import { subscribeComponent } from '@/stomp'

const columns: TableColumnsType = [
  {
    title: 'Наименование',
    dataIndex: 'name'
  },
  {
    title: 'Контакты представителя',
    dataIndex: 'contacts'
  },
]

type TeamData = {
  name: string
  contacts: string
}

const loading = ref(false)
const load = trackingLoading(
  handlingError(loader<TeamData>('/api/team'), displayErrorMessage),
  loading
)
const data = useFetch(load)
subscribeComponent('/team', async () => data.value = await load())

const open = ref(false)

function openForm() {
  open.value = true
}
</script>

<template>
  <a-card title="Команды" style="height: 100%">
    <a-space direction="vertical" style="width: 100%">
      <a-button type="primary" @click="openForm">Добавить</a-button>
      <a-table :columns="columns"
               :data-source="data"
               :loading="loading"
               :pagination="false"
               bordered/>
    </a-space>
    <NewTeamView v-model:open="open"/>
  </a-card>
</template>