<script setup lang="ts">
import { reactive, ref } from 'vue'
import { requiredRule } from '@/validation'
import axios from 'axios'
import type { FormInstance } from 'ant-design-vue'
import dayjs, { Dayjs } from 'dayjs'
import { displayErrorMessage, handlingError, loader, useFetch } from '@/fetch'
import type { DefaultOptionType } from 'ant-design-vue/es/vc-select/Select'

defineProps<{
  open: boolean
}>()

const emit = defineEmits<{
  'update:open': [open: boolean]
}>()

type FormState = {
  gymId?: number
  date: Dayjs
  entryTime?: Dayjs
  startTime?: Dayjs
  ownerId?: number
  guestId?: number
}

const formState = reactive<FormState>({
  date: dayjs()
})

const rules = {
  gymId: [requiredRule],
  date: [requiredRule],
  entryTime: [requiredRule],
  startTime: [requiredRule],
  ownerId: [requiredRule],
  guestId: [requiredRule],
}

const gymOptions = useFetch<Array<DefaultOptionType>>(loader('/api/game/gym-options'))
const ownerOptions = useFetch<Array<DefaultOptionType>>(loader('/api/game/owner-options'))
const guestOptions = useFetch<Array<DefaultOptionType>>(loader('/api/game/guest-options'))

const formRef = ref<FormInstance>()

async function onSave() {
  try {
    await formRef.value?.validate()
    await submitThenClose()
  } catch (validationErrors: any) {
    console.log(validationErrors)
  }
}

const submit = () => axios.post('/api/game', formatForBackend(formState))
const submitThenClose = handlingError(() => submit().then(close), displayErrorMessage)

function formatForBackend(formState: FormState) {
  return {
    ...formState,
    date: formState.date.format('YYYY-MM-DD'),
    entryTime: formState.entryTime?.format('HH:mm'),
    startTime: formState.startTime?.format('HH:mm')
  }
}

function close() {
  emit('update:open', false)
}
</script>

<template>
  <a-modal :open="open"
           @update:open="(val: boolean) => emit('update:open', val)"
           title="Новая игра"
           >
    <a-form :model="formState" :rules="rules" layout="vertical" ref="formRef">
      <a-form-item label="Зал" name="gymId">
        <a-select v-model:value="formState.gymId" :options="gymOptions"/>
      </a-form-item>
      <a-form-item label="Дата" name="date">
        <a-date-picker v-model:value="formState.date" style="width: 100%"/>
      </a-form-item>
      <a-form-item label="Время входа" name="entryTime">
        <a-time-picker v-model:value="formState.entryTime" format="HH:mm" style="width: 100%"/>
      </a-form-item>
      <a-form-item label="Время свистка" name="startTime">
        <a-time-picker v-model:value="formState.startTime" format="HH:mm" style="width: 100%"/>
      </a-form-item>
      <a-form-item label="Хозяева" name="ownerId">
        <a-select v-model:value="formState.ownerId" :options="ownerOptions"/>
      </a-form-item>
      <a-form-item label="Гости" name="guestId">
        <a-select v-model:value="formState.guestId" :options="guestOptions"/>
      </a-form-item>
    </a-form>
    <template #footer>
      <a-button @click="onSave" type="primary">Сохранить</a-button>
      <a-button @click="close">Отмена</a-button>
    </template>
  </a-modal>
</template>

<style scoped>

</style>