function getAverageAmount(){
	
	
	let url='http://localhost:7000/reim/getAvg';
	
    let xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let reims = JSON.parse(xhr.response)
			console.log(reims);
			 let h = 1;
			for(let i = 0; i<reims.length; i++){

				 let display = document.getElementById('myTable');

                let newRow = display.insertRow();
				 let cell1 = newRow.insertCell(0);
                let cell2 = newRow.insertCell(1);
                
               
                cell1.innerHTML ="Employee 0"+h;
                h++; 
                cell2.innerHTML = reims[i];
			}
		}
	}
	
	 xhr.open('GET', url);
    xhr.send();
}

let call = document.getElementById("check");
call.addEventListener("click", getAverageAmount);