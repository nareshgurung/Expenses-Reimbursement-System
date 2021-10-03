
let form = document.getElementById('mysubmit');


// form.addEventListener('click', login);

function login() {

    let usr = document.querySelector("#username").value;
    let pass = document.querySelector("#password").value;

    let user = {
        username: usr,
        password: pass
    }
    console.log(user);

    
    let url = "http://localhost:7000/reim/login";
    let xhr = new XMLHttpRequest();
    fetch(url, { 
		method: 'POST',
	    headers: {
			'Content-Type': 'application/json',
		},
     })
        .then(response => {
            if (response.redirected) {
                window.location.href = response.url;
            }
        })
        .catch(err => console.log(err + " url: " + url));
};
