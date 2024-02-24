import SockJS from 'sockjs-client/dist/sockjs.min.js'
import { Client, type messageCallbackType } from '@stomp/stompjs'
import { getCurrentInstance, onBeforeUnmount } from 'vue'
import { removeByRef } from '@/util'

export const stompClient = new Client({
  reconnectDelay: 5000,
  heartbeatIncoming: 4000,
  heartbeatOutgoing: 4000,
  webSocketFactory: () => { return new SockJS('/websocket') }
})

const connectListeners: Array<() => void> = []
stompClient.onConnect = () => connectListeners.forEach(listener => listener())

stompClient.activate()

/**
 * Подписывает текущий компонент на топик. При отключении компонента отписывается от топика.
 * @param topic
 * @param callback
 */
export function subscribeComponent(topic: string, callback: messageCallbackType) {
  const component = getCurrentInstance()
  const subscribe = () => {
    const subscription = stompClient.subscribe('/topic' + topic, callback)
    onBeforeUnmount(() => subscription.unsubscribe(), component)
  }
  if (stompClient.connected) {
    subscribe()
  }
  connectListeners.push(subscribe)
  onBeforeUnmount(() => removeByRef(connectListeners, subscribe), component)
}