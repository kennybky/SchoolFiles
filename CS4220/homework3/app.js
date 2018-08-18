
let chuck = new Vue({
    el: '#chuck',

    // The data that will bind to our template
    data: {
        appName: 'Chuck Norris Jokes',
        currentFact: '',
        searchHistory: [],
        isFetching: false,
        categories:["all"],
        selected:"",
        search_key:"",
        search_results:[],
        displayFact: false,
        displaySearch: false,
        search_header : ""
    },

    methods:{

        getCategories: function(){
            

            
            let viewModel = this

            axios.get("https://api.chucknorris.io/jokes/categories", {
                headers: {
                    Accept: 'application/json'
                }
            })
            .then(function(response){

                console.log(response)

                
                response.data.forEach(element => {
                    viewModel.categories.push(element)
                });

                
            })
            .catch(function(err){
                alert(err)
            })
        },

        search : function() {
            let viewModel = this
            viewModel.displayFact = false;
            viewModel.isFetching = true
            let key = this.search_key;
            viewModel.searchHistory.unshift(key)
            let url = `https://api.chucknorris.io/jokes/search?query=${key}`;
            axios.get(url, {
                headers: {
                    Accept: 'application/json'
                }
            })
            .then(function(response){
                viewModel. search_results = []
                if(response.data.result.length < 1) {
                   alert("No Chuck Norris Jokes found. You don't find Chuck, He finds you...")
                   viewModel.isFetching = false
                   viewModel.displaySearch = false;
                   return;
                }
                response.data.result.forEach(val => {
                   
                  let batch = val.value.replace(new RegExp(viewModel.search_key, "gim"), match => {
                    return `<span class="highlightText">${match}</span>`;
                });
                viewModel.search_results.push(batch)
                viewModel.search_header = `Search Results for '<span class="highlightText">${viewModel.search_key}</span>'`
                

                });
                
                viewModel.isFetching = false
                viewModel.displaySearch = true;
            
    
                
            })
            .catch(function(err){
                alert(err)
                viewModel.isFetching = false;
                viewModel.displaySearch = false;
            })
           
        },

        getFact : function(){
            let viewModel = this
            viewModel.displaySearch = false;
            viewModel.isFetching = true
            let cat = this.selected;
            let url = "https://api.chucknorris.io/jokes/random"
            if (cat !="all") {
                url += `?category=${cat}`
            }

            axios.get(url, {
                headers: {
                    Accept: 'application/json'
                }
            })
            .then(function(response){

                viewModel.isFetching = false
                viewModel.displayFact = true;
               
                
            
            
                viewModel.currentFact = response.data.value
                
            })
            .catch(function(err){
                alert(err)
                viewModel.isFetching = false;
            })
        },

        history : function(){
            this.isFetching = false
            this.displayFact = false;
            this.displaySearch = true;
            this.search_results = this.searchHistory;
            this.search_header = `Search History`
        }
    }
})

chuck.getCategories();
