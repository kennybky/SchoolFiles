<template>
<div class="columns">
  <div class="column is-four-fifths">
    <label class="checkbox">
      <input type="text" v-show="todo.edit" v-model="todo.description" @keyup.enter="todo.edit = false">
      <span v-show="!todo.edit"><input type="checkbox" v-model="todo.done"> <span :class="{done: todo.done}">{{todo.description}}</span></span>
      
    </label>

  </div>
  <div class="column has-text-right">
    <span class="icon" @click="deleteTodo(course, todo)">
      <i class="fa fa-trash has-text-danger"></i>
    </span>
    <span class="icon" @click="edit(course, todo)">
      <i class="fa fa-edit has-text-info"></i>
    </span>
  </div>
</div>
</template>


<script>
  import { store } from '../store.js';
 
  export default {
      name: 'CourseTodo',
      data () {
          return {
          }
      },
      props: ['todo', 'course'],
      methods: {
       deleteTodo(course, todo){
           store.delete(course, todo)
       },
       edit(course, todo){
        store.edit(course, todo)
       },
       
      }
  }
</script>

<style>
.fa-trash {
    color: red;
}
.done {
  text-decoration: line-through;
}
</style>