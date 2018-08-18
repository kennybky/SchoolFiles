import Vue from 'vue';
import { seedData } from './seed.js';

export const store = {
  state: {
    seedData
  },
  getActiveCourse() {
    return this.state.seedData.find((course) => course.active)
  },
  setActiveCourse(courseId) {
    this.state.seedData.map((courseObj) => {
      courseObj.id === courseId ? courseObj.active = true : courseObj.active = false;
    })
  },
  submitTodo(todoDescription) {
    const activeCourse = this.getActiveCourse();
    activeCourse.todos.push({
      description: todoDescription,
      done: false,
      edit: false
    })
  },

  deleteToDo(courseId, todo){
    const course = this.state.seedData.find((course) => course.id == courseId)
    let todoIndex  = course.todos.indexOf(todo);
    course.todos.splice(todoIndex, 1);
  },

  editToDo(todo) {
    this.state.seedData.map((course)=>{
      course.todos.map((obj) => {
        obj.edit = false;
        
        })
    })
    todo.edit = true;
  },
  archiveToDo() {
    this.state.seedData.map((course)=>{

      course.todos = course.todos.filter((todo)=>{
        return !todo.done;
      })

    })
   


  
  }

}
