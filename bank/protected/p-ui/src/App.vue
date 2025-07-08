<template>
  <div class="main-content">
    <div class="content">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
.main-content {
  padding: 20px;
}
</style>
