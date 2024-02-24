import { type MaybeRef, type Ref, ref, toValue, watchEffect } from 'vue'
import axios from 'axios'
import { message } from 'ant-design-vue'

type Params = Record<string, any> | URLSearchParams

export function useFetch<T>(load: () => Promise<T>) {
  const data = ref<T>()
  
  watchEffect(async () => data.value = await load())

  return data
}

export function loader<T>(url: string, params?: MaybeRef<Params>) {
  return async () => {
    const config = {
      params: toValue(params)
    }
    return (await axios.get<T>(url, config)).data
  }
}

export function trackingLoading<T>(load: () => Promise<T>, loading: Ref<boolean>) {
  return async () => {
    try {
      loading.value = true
      return await load()
    } finally {
      loading.value = false
    }
  }
}

export function handlingError<T>(load: () => Promise<T>, handler: (error: any) => any) {
  return async () => {
    try {
      return await load()
    } catch (error) {
      return handler(error)
    }
  }
}

export function displayErrorMessage(error: any) {
  message.error(error.message, 5)
}