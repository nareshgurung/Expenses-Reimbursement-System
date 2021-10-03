

let submitForm = document.getElementById('mysubmit');


submitForm.addEventListener('click', getAllReim);

function getAllReim() {


    let url = 'http://localhost:7000/reim/authorReim';

    let xhr = new XMLHttpRequest();
    console.log(xhr);

    // console.log(sessionStorage.getAllReim);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let reims = JSON.parse(xhr.response)
            // console.log(reims);
            console.log(reims);
            for (let i = 0; i < reims.length; i++) {

                console.log(reims.length);
                let display = document.getElementById('myTable');


                let newRow = display.insertRow();
                console.log(newRow);
                let cell1 = newRow.insertCell(0);
                let cell2 = newRow.insertCell(1);
                let cell3 = newRow.insertCell(2);
                let cell4 = newRow.insertCell(3);
                let cell5 = newRow.insertCell(4);
                let cell6 = newRow.insertCell(5);

                cell1.innerHTML = reims[i]
                i++;
                cell2.innerHTML = reims[i]
                i++;
                cell3.innerHTML = reims[i]
                i++;
                cell4.innerHTML = reims[i]
                i++;
                cell5.innerHTML = reims[i]
                i++;
                cell6.innerHTML = reims[i]


            }
        }
    }

    xhr.open('GET', url);
    xhr.send();
}



window.onload = function () {
    this.getAllReim();
}

let logout = document.querySelector('#logout');
logout.addEventListener('click', async () =>{
	await fetch('http://localhost:7000/reim/logout');
})