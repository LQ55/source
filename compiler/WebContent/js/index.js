function importFile() {
		$("#file").click();
	}

	function readFile() {
		//读取文件File对象
		var selectedFile = document.getElementById("file").files[0];
		var filename = selectedFile.name;
		var filesize = selectedFile.size;
		console.log(selectedFile);
		var houzui = filename.substring(filename.lastIndexOf("."));
		console.log("选中文件" + filename + "，文件大小：" + filesize);
		if (!('.java' === houzui)) {
			alert("请选择以.java结尾的文件");
			return;
		}
		var reader = new FileReader();
		//读取完成时的回掉，文件数据存放于result中
		reader.onload = function() {
			console.log(this);
			console.log(this.result);
			document.getElementById("code").innerHTML = this.result;
			document.getElementById("filenamespan").innerHTML = filename;
		}
		//以文本方式读取文件，格式为UTF-8
		reader.readAsText(selectedFile, "UTF-8");
	}
	//CTRL + S保存
	document.addEventListener("keydown",function(e){
		 if (e.keyCode == 83 && (navigator.platform.match("Mac") ? e.metaKey : e.ctrlKey)&& document.activeElement === document.getElementById("code")){
		        e.preventDefault();
		        var blob = new Blob(["Hello, world!"], {type: "text/plain;charset=utf-8"});
		        saveAs(blob, "D://hello world.txt");
		 }
	});