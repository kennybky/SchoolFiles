<template>
<button  @click="archive()">Archive</button>
</template>


<script>
  import { store } from '../store.js';
  export default {
      name: 'Archive',
      data () {
          return {
          }
      },
      methods: {
       
       archive(){
        store.archive()
       }
      }
  }
</script>

<style>
.fa-archive {
    color: blue;
}

</style>