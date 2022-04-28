const opener = document.querySelectorAll(".opener");
const cont = document.querySelectorAll(".content");
const arrow = document.querySelectorAll(".opener i");

function dropdown(open, content , icon ){
	for (let i = 0; i < open.length; i++) {
		open[i].addEventListener("click", function(){
			content[i].classList.toggle("content-active");
			for (var j = 0; j < open.length; j++) {
				if(i != j){
					content[j].classList.remove("content-active");
				}
			}
		});
	}
}

dropdown( opener, cont,  arrow );

