// let updateRow = document.getElementById("alter");

let updateForm = document.getElementById("update");

updateForm.addEventListener('click', updateReim);


function updateReim() {
    let url = 'http://localhost:7000/reim/update';
    let reimId = document.querySelector("#id").value;
    console.log(reimId);
    let status = document.querySelector("#status").value;
	console.log(status);
    let reim = {
        reimId: reimId,
        status: status
    }
    let xhr = new XMLHttpRequest();

    if (xhr.readyState === 4 && xhr.status === 200) {
        body: JSON.stringify(reim);
    }

    xhr.open('POST', url);
    xhr.send();
}

function getAllReim() {

	
    let url = 'http://localhost:7000/reim/getallreim';

    let xhr = new XMLHttpRequest();
    console.log(xhr);

    // console.log(sessionStorage.getAllReim);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let reims = JSON.parse(xhr.response);
            console.log(reims);

            for (let i = 0; i<reims.length; i++) {

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
                cell3.innerHTML = reims[i];
                i++;
                cell4.innerHTML = reims[i];
                i++;
                cell5.innerHTML = reims[i];
                i++;
                cell6.innerHTML = reims[i];
            }
        }
    }

    xhr.open('GET', url);
    xhr.send();
}

window.onload = function () {
    this.getAllReim();
}
let populateTable = document.getElementById('showTable');
populateTable.addEventListener('click', getAllReim);

function updateTableByStatus() {
	let id = document.getElementById("selectStatus").value;
    console.log(id);
    
	let url = `http://localhost:7000/rem/getByStatus/${id}`;
	
               
	 
	let xhr = new XMLHttpRequest();
    console.log(xhr);
    
    xhr.onreadystatechange = function () {
	if(xhr.readyState ===4 && xhr.status === 200){
		
		var reims = JSON.parse(xhr.response);
		// let arr = 
		console.log(reims);
		
		for (let i = 0; i<reims.length; i++) {

				let newRow = document.createElement("tr");
				
				 if(id == 6){
                	newRow.style.backgroundColor = "lightyellow";	
					}
				else if(id == 9){
                	newRow.style.backgroundColor = "lightgreen";											
					}
				else if(id == 10){
					newRow.style.backgroundColor = "magenta";
					
				}
					
                let display = document.getElementById('myTable');
      
				display.appendChild(newRow);
				
					let cell1 = document.createElement('td');					
					let cell2 = document.createElement('td');
					let cell3 = document.createElement('td');
					let cell4 = document.createElement('td');
					let cell5 = document.createElement('td');
					let cell6 = document.createElement('td');

					cell1.innerText = reims[i];
	                i++;
	                cell2.innerText = reims[i];
	                i++;
	                cell3.innerText = reims[i];
	                i++;
	                cell4.innerText = reims[i];
	                i++;
	                cell5.innerText = reims[i];
	                i++;
	                cell6.innerText = reims[i];
	                
	                newRow.appendChild(cell1);
	
	                newRow.appendChild(cell2);
	        
	                newRow.appendChild(cell3);
	           
	                newRow.appendChild(cell4);
	          
	                newRow.appendChild(cell5);
	          
	                newRow.appendChild(cell6);
	                
	                display.appendChild(newRow);    
            }
	}
}

	xhr.open('GET', url);
    xhr.send();
};

function removeTable(){
	let tbl=  document.getElementById('myTable');
		tbl.deleteRow(-1);
}
let rmv = document.getElementById("removeButtun");
rmv.addEventListener("click", removeTable);

let pendingStatus = document.getElementById("selectStatus");

pendingStatus.addEventListener('change', updateTableByStatus);


window.onload = function () {
    this.updateTableByStatus();
}

let logout = document.querySelector('#logout');
logout.addEventListener('click', async () =>{
	await fetch('http://localhost:7000/reim/logout');
})
