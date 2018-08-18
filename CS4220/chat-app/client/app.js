// Chat Component
const chatComponent = {
    template: `<div class="chat-box">
                   <p v-for="data in content">
                       <img v-bind:src="data.user.avatar" class="circle" width="30px"/>
                       <span><strong>{{data.user.name}}</strong> <small>{{data.date}}</small><span>
                       <br />
                       {{data.message}}
                   </p>
               </div>`,
    props: ['content']
}

// Users Component
const usersComponent = {
    template: ` <div class="user-list">
                   <h6>Active Users ({{users.length}})</h6>
                   <ul v-for="user in users">
                       <li>
                       <img v-bind:src="user.avatar" class="circle" width="30px"/>
                            <span>{{user.name}}</span>
                       </li>
                       <hr>
                   </ul>
               </div>`,
    props: ['users']
}

const welcomeComponent = {
    template: `<div class="teal center-align lighten-2">
                <div>Welcome</div>
              <div><img v-bind:src="user.avatar" class="circle" width="60px"/></div>
    <div><span><strong>{{user.name}}</strong></span></div>
    </div>`,
    props: ['user']
}


// Welcome Component


const socket = io()
const app = new Vue({
    el: '#chat-app',
    data: {
        loggedIn: false,
        userName: '',
        user: {},
        users: [],
        message: '',
        messages: [],
        loginerror: ''
    },
    methods: {
        joinUser: function () {
            if (!this.userName)
                return

            socket.emit('join-user', this.userName)
        },
        sendMessage: function () {
            if (!this.message)
                return

            socket.emit('send-message', { message: this.message, user: this.user })
        }
    },
    components: {
        'users-component': usersComponent,
        'chat-component': chatComponent,
        'welcome-component': welcomeComponent
    }
})


// Client Side Socket Event
socket.on('refresh-messages', messages => {
    app.messages = messages
})
socket.on('refresh-users', users => {
    app.users = users
})

socket.on('successful-join', user => {
    // the successful-join event is emitted on all connections (open browser windows)
    // so we need to ensure the loggedIn is set to true and user is set for matching user only
    if (user.name === app.userName) {
        app.user = user
        app.loggedIn = true
    }

    app.users.push(user)
    app.loginerror = ''
    
})

socket.on('successful-message', content => {
    // clear the message after success send
    app.message = ''
    app.messages.push(content)
})

socket.on('failed-join', data => {
    if (data.id == socket.id) {
    app.loginerror = data.message;
    }
})
