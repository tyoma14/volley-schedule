<script setup lang="ts">
import { CalendarOutlined, HomeOutlined, TeamOutlined } from '@ant-design/icons-vue'
import { ref } from 'vue'

const collapsed = ref<boolean>(false)
const selectedKeys = ref<string[]>(['1'])
</script>

<template>
  <a-layout style="min-height: 100vh">
    <a-layout-sider v-model:collapsed="collapsed" collapsible>
      <a-menu v-model:selectedKeys="selectedKeys" :selectable="false" theme="dark" mode="inline">
        <a-menu-item>
          <router-link to="/game-schedule">
            <CalendarOutlined />
            <span>График игр</span>
          </router-link>
        </a-menu-item>
        <a-menu-item>
          <router-link to="/gym">
            <HomeOutlined />
            <span>Залы</span>
          </router-link>
        </a-menu-item>
        <a-menu-item>
          <router-link to="/team">
            <team-outlined />
            <span>Команды</span>
          </router-link>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-content style="margin: 0 16px">
        <router-view></router-view>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        Created by Artyom Zheltyshev
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>

<style scoped>
[data-theme='dark']  {
  background: #141414;
}
</style>
