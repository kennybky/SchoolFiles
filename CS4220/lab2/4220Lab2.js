	//Question 1
	const hashNonce = (str) =>{
			const crypto = require('crypto')
			const hashingAlgorithm = 'sha256';

			let found = false
			let nonce = 1;
			let strn = ""
			let message = "";
			while(!found) {
			hash = crypto.createHash(hashingAlgorithm);
			message = str + nonce;
			hash.update(message);
			let digest = hash.digest('hex')
			if(digest.substr(0,3) != "000") {
				nonce++
			} else {
				found = true;
				strn = digest;
			}
			}

			console.log(`The 'SHA-256' digest of '${message}' is: ${strn}`)

	}


//Question 2

const verify = () =>{
	var request = require('request');
	const url = "http://albertcervantes.com/cs4220/messages.json"
	request(url, (error, response, body) => {
        	try {
      			const parsedData = JSON.parse(body);
      				  
    			verifyAndPrint(parsedData);

    		} catch (e) {
      			console.error(e.message);
    		}
	})
}

//Question 2 (Put it in a seperate fucntion for readability)
const verifyAndPrint = (data) =>{
	const crypto = require('crypto'),
	    fs = require('fs'),    
	    path = require('path'),
		fullPublicKeyPath = path.resolve('keys', 'public_key.pem'),
	    publicKey = fs.readFileSync(fullPublicKeyPath, 'utf8')

    data.forEach((datum)=>{

    const message = datum.message,
    	  signature = datum.signature,

    	  verify = crypto.createVerify('SHA256')
    verify.update( message )

    // Determine the validity of the signature for the data and public key.
    const isValid = verify.verify(publicKey, signature, 'hex')

    console.log(`${isValid} - ${message}`)


    }) 
}

//Question 1 Test
hashNonce("Hello, World!")


//Question 2 Test
verify()


