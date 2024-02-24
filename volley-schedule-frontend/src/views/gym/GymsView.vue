<script setup lang="ts">
import { type TableColumnsType } from 'ant-design-vue'
import { ref } from 'vue'
import NewGymView from '@/views/gym/NewGymView.vue'
import { displayErrorMessage, handlingError, loader, trackingLoading, useFetch } from '@/fetch'
import { subscribeComponent } from '@/stomp'

const columns: TableColumnsType = [
  {
    title: 'Наименование',
    dataIndex: 'name',
  },
  {
    title: 'Адрес',
    dataIndex: 'address'
  },
]

type GymData = {
  name: string
  address: string
}

const loading = ref(false)
const load = trackingLoading(
  handlingError(loader<GymData>('/api/gym'), displayErrorMessage),
  loading
)
const data = useFetch(load)
subscribeComponent('/gym', async () => data.value = await load())

const opened = ref(false)

function openForm() {
  opened.value = true
}
</script>

<template>
  <a-card title="Залы" style="height: 100%">
    <a-space direction="vertical" style="width: 100%">
      <a-button type="primary" @click="openForm">Добавить</a-button>
      <a-table :columns="columns"
               :data-source="data"
               :loading="loading"
               :pagination="false"
               bordered>
      </a-table>
    </a-space>
    <NewGymView v-model:open="opened"/>
  </a-card>
</template>
