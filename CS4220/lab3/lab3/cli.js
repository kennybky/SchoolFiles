const
    app = require('./app'),
    yargs = require('yargs')


const flags = yargs.usage('$0: Usage <cmd> [options]')
    .command({
        command: 'play',
        desc: 'Starts playing the card game',
        handler: () => { app.play() }
    })
    .help('help')
    .argv