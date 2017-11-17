<template>
  <li>
      {{title}}
      <button @click="$emit('remove')">X</button>
  </li>
</template>
<script>
export default {
  props:['title']
}
</script>
