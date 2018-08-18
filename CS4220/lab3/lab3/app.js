const
    cards = require('deckofcards'),
    inquirer = require('inquirer')

    const draw = (shuffle, n = 1) => {
        cards.deck(shuffle)
            .then(deck => cards.draw(deck.deck_id, n))
            .then(result => {
                console.log('-- CARDS --')
                result.cards.forEach(card => {
                    console.log(`${card.value} of ${card.suit}`)
                })
    
                console.log('-- REMAING CARDS --')
                console.log(result.remaining)
            })
            .catch(err => console.log(err))
    }


const discardPrompt = (result) => {
    return inquirer.prompt([{
        type: 'checkbox',
        message: 'select cards to throw away',
        name: 'cards',
        choices: result,  
        validate: (answer)=> {
            if (answer.length > 4) {
              return 'You must cannot throw away more that 4 cards.';
            }
            return true;
    }
}])
}


const findAndRemove = (current, throwaway) => {
    return current.filter(card=> throwaway.indexOf(card.code) == -1)
}


const print = cards => {
    console.log('-- CARDS--')
    cards.forEach(card=>{
        console.log(`${card.value} of ${card.suit}`)
    })
}

const play = () => {
        let card_deck;
        cards.deck(true)
            .then(deck =>{ 
                card_deck = deck
                return cards.draw(deck.deck_id, 5)
            }
        )
            .then(result => {
                let hands = []
                
                result.cards.forEach(card => {
                    let hand = new Object()
                    hand.name = `${card.value} of ${card.suit}`
                    hand.value = card.code
                    hands.push(hand)
                })
                
                discardPrompt(hands).then(answers=>{
                    let n = answers.cards.length;
                    result.cards = findAndRemove(result.cards, answers.cards)
                    cards.draw(card_deck.deck_id, n).then(fresh=>{
                        result.cards = result.cards.concat(fresh.cards)
                        print(result.cards);
                        console.log('\n--REMAINING CARDS--')
                        console.log(fresh.remaining)
                    })
    
                    
    
                })
            })
            .catch(err => console.log(err))
}

module.exports = {
    play
}
