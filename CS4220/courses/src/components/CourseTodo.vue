<template>
<div class="columns">
  <div class="column is-four-fifths">
    <label class="checkbox">
      <input type="text" v-show="todo.edit" v-model="todo.description" @keyup.enter="finishEdit(todo)">
      <span v-show="!todo.edit"><input type="checkbox" v-model="todo.done"> <span :class="{done: todo.done}">{{todo.description}}</span></span>
      
    </label>

  </div>
  <div class="column has-text-right">
    <span class="icon" @click="deleteToDo(course.id, todo)">
      <i class="fa fa-trash has-text-danger"></i>
    </span>
    <span class="icon" @click="editToDo(todo)">
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
       deleteToDo(courseId, todo){
           store.deleteToDo(courseId, todo)
       }, 
       editToDo(todo){
        store.editToDo(todo)
       },
       finishEdit(todo){
         todo.edit = false;
       }
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