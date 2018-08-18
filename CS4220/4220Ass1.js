	//Question 1
	function upperCase(chr){
	    let str = "";
	    for (let i=0; i < chr.length; i++) { 
	    	if (chr.charCodeAt(i) > 96 && chr.charCodeAt(i) < 123 ) {
	    		str += String.fromCharCode(chr.charCodeAt(i) - 32)
	    	}
	    	else{ 
	    		str += chr.charAt(i) 
	    	}  
	    } 
	    return str
	}

	 //..

	 function lowerCase(chr){
	   let str = ""
	    for (let i=0; i < chr.length; i++) { 
	    	if (chr.charCodeAt(i) > 64 && chr.charCodeAt(i) < 91 ) {
	    		str +=String.fromCharCode(chr.charCodeAt(i) + 32)
	    	}
	    	else { 
	    		str += chr.charAt(i)
	    	}
	        
	    } 
	    return str; 
	}

	//..


	function sentenceCase(chr, uc) {
		let str = "";
		let fl = true;
		for (let i=0; i < chr.length; i++) {
			if (fl == true && chr.charAt(i) !=" " ) {
				str += upperCase(chr.charAt(i))
				fl = false;
				let m = i+1;
				while (chr.charAt(m) !=" " && chr.charAt(m) !="." && chr.charAt(m) !="," && m < chr.length) {
					str += lowerCase(chr.charAt(m));
					m++;
				}
				i = m-1;
			} else if(chr.charAt(i) ==" " || chr.charAt(i) ==","){
				str += chr.charAt(i);
			} else if(chr.charAt(i) =="."){
				str += ".";
				fl = true;
			}else {
				let k = i+1;
				let agg = upperCase(chr.charAt(i));
				while (chr.charAt(k) !=" " && chr.charAt(k) !="." && chr.charAt(k) !=","  && k < chr.length) {
					agg += lowerCase(chr.charAt(k));
					k++;
				}

				if (uc.indexOf(agg) > -1) {
					str+= agg;
				}
				else {
					str += lowerCase(agg);
				}

				i = k-1;

			}
		}
		return str;

	}

	function capitalizedCase(chr) {
		let str = "";
		let fl = true;
		for (let i=0; i < chr.length; i++) {
			if (fl == true && chr.charAt(i) !=" " ) {
				str += upperCase(chr.charAt(i))
				fl = false;
			} else if(chr.charAt(i) ==" " || chr.charAt(i) =="."){
				str += chr.charAt(i);
				fl = true;
			}else {
				str += lowerCase(chr.charAt(i));
			}
		}
		return str;

	}


	function alternatingCase(chr) {
		let str = "";
		let start = 0;
		for (let i=0; i < chr.length; i++) {
		    if(start % 2 == 0) {
				str += lowerCase(chr.charAt(i));
				start++
			} else {
				str += upperCase(chr.charAt(i));
				start++
			}
		}
		return str;
	}

function titleCase(chr, lc) {
		let str = "";
		let fl= true;
		for (let i=0; i < chr.length; i++) {
			if(chr.charAt(i) ==" " || chr.charAt(i) ==","){
				str += chr.charAt(i);
			} else if (chr.charAt(i) ==".") {
				str += chr.charAt(i);
				fl = true
			}
			else {
				let k = i+1;
				let agg = upperCase(chr.charAt(i));
				while (chr.charAt(k) !=" " && chr.charAt(k) !="." && chr.charAt(k) !=","  && k < chr.length) {
					agg += lowerCase(chr.charAt(k));
					k++;
				}

				if (lc.indexOf(lowerCase(agg)) > -1 && fl == false) {
					str+=lowerCase(agg);
				}
				else {
					str += agg;
					fl = false;
				}

				i = k-1;

			}
		}
		return str;

	}

	function inverseCase(chr) {
		let str = "";
		let fl = true;
		for (let i=0; i < chr.length; i++) {
			if (fl == true && chr.charAt(i) !=" " ) {
				str += lowerCase(chr.charAt(i))
				fl = false;
			} else if(chr.charAt(i) ==" " || chr.charAt(i) =="."){
				str += chr.charAt(i);
				fl = true;
			}else {
				str += upperCase(chr.charAt(i));
			}
		}
		return str;

	}


//Question 1 Test====
function runStringFunctions(){
    let str = 'I watched the storm, so beautiful yet terrific. The face of the moon was in shadow.'
    
    let unconditionallyCapitalized = ['I', 'Moon', 'Shadow']
    let lowercaseWords = ['the', 'of', 'in', 'an']
    
    console.log( 'upperCase: ', upperCase(str) )
    console.log( 'lowerCase: ', lowerCase(str) )
    console.log( 'sentenceCase: ', sentenceCase(str, unconditionallyCapitalized) )
    console.log( 'capitalizedCase: ', capitalizedCase(str) )
    console.log( 'alternatingCase: ', alternatingCase(str) )
    console.log( 'titleCase: ', titleCase(str, lowercaseWords) )
    console.log( 'inverseCase: ', inverseCase(str) )
} 
//======


//Question 2

	function getCharacterFrequency(str) {
    let obj = new Object()
    for (let i = 0; i < str.length; i++) {
    	let current = lowerCase(str.charAt(i));
        if (obj[current] === undefined) {
            obj[current] = 1
                } else {
                    obj[current] +=1
                }
    } return obj
}


function printCharacterFrequency(obj) {
    for (key in obj) {
    	value = obj[key]
    	let message = "'" + key + "' occurs " + value;
    	if (value > 1) {
    		message += " times ";
    	} else {
    		message += " time ";
    	}
        
        console.log(message)
    }
}


//Question 2 Test
function runCharacterFunctions(){

    let str = 'Hello, World!'
    
    let frequencyObj = getCharacterFrequency( str )
    
    printCharacterFrequency( frequencyObj )

}
