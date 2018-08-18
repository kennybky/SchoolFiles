<template>
<button  @click="archiveToDo()"><span>Archive Completed</span><span class="icon">
      <i class="fa fa-archive"></i>
    </span></button>
</template>


<script>
  import { store } from '../store.js';
  export default {
      name: 'ToDoArchive',
      data () {
          return {
          }
      },
      methods: {
       
       archiveToDo(){
        store.archiveToDo()
       }
      }
  }
</script>

<style>
.fa-archive {
    color: blue;
}

</style>