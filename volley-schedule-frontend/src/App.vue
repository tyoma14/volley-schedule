<script setup lang="ts">
import { RouterView } from 'vue-router'
import ru_RU from 'ant-design-vue/es/locale/ru_RU';
import dayjs from 'dayjs'
import 'dayjs/locale/ru';

dayjs.locale('ru');
</script>

<template>
  <a-config-provider :locale="ru_RU">
    <RouterView />
  </a-config-provider>
</template>
