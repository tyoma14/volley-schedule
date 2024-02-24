import { createRouter, createWebHistory } from 'vue-router'
import Main from '../views/MainView.vue'
import GameSchedule from '@/views/game/GameScheduleView.vue'
import GymsView from '@/views/gym/GymsView.vue'
import TeamsView from '@/views/team/TeamsView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: Main,
      children: [
        {
          path: 'game-schedule',
          component: GameSchedule
        },
        {
          path: 'gym',
          component: GymsView
        },
        {
          path: 'team',
          component: TeamsView
        }
      ]
    },
  ]
})

export default router
