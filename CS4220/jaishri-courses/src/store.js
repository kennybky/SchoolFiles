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

  edit(course, todo) {
    this.state.seedData.map((courseObj)=>{
    courseObj.todos.map((o) => {
    if (o == todo) {
      o.edit = true;
    } else {
      o.edit = false;
    }
    
    })
  })
    
  },

  delete(course, todo){
    let i = course.todos.indexOf(todo);
    course.todos.splice(i, 1);
  },

  
  archive() {
    this.state.seedData.map((course)=>{
      let done = []
       course.todos.forEach((todo)=>{
        if(todo.done){
          done.push(todo)
        }
      })

      done.forEach((todo) =>{
        this.delete(course, todo);
      })

    })
   


  
  }

}
