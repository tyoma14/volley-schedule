<script setup lang="ts">
import { reactive, ref } from 'vue'
import { requiredRule } from '@/validation'
import axios from 'axios'
import { type FormInstance, message } from 'ant-design-vue'

defineProps<{
  open: boolean
}>()

const emit = defineEmits<{
  'update:open': [open: boolean]
}>()

type FormState = {
  name: string
  contacts: string
}

const formState = reactive<FormState>({
  name: '',
  contacts: ''
})

const rules = {
  name: [requiredRule],
  contacts: [requiredRule]
}

const formRef = ref<FormInstance>()

async function onSave() {
  try {
    await formRef.value?.validate()
    await submit()
    close()
  } catch (validationErrors: any) {
    console.log(validationErrors)
  }
}

async function submit() {
  await axios.post('/api/team', formState)
}

function close() {
  emit('update:open', false)
}
</script>

<template>
  <a-modal :open="open"
           @update:open="(val: boolean) => emit('update:open', val)"
           title="Новая команда"
           >
    <a-form :model="formState" :rules="rules" layout="vertical" ref="formRef">
      <a-form-item label="Наименование" name="name">
        <a-input v-model:value="formState.name" />
      </a-form-item>
      <a-form-item label="Контакты представителя" name="contacts">
        <a-input v-model:value="formState.contacts" />
      </a-form-item>
    </a-form>
    <template #footer>
      <a-button @click="onSave" type="primary">Сохранить</a-button>
      <a-button @click="close">Отмена</a-button>
    </template>
  </a-modal>
</template>