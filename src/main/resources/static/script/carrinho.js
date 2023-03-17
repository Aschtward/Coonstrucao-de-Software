var totalCarrinho;

function carregarCompras() {
	let carrinho = localStorage.getItem('carrinho');
	totalCarrinho = 0;
	if (carrinho != null) {
		carrinho = JSON.parse(carrinho);
		var listaProdutos = document.getElementById("lista-produtos-compra");
		listaProdutos.innerHTML = "";
		for (var i = 0; i < carrinho.length; i++) {
			var produto = carrinho[i];
			var itemLista = document.createElement('li');
			document.getElementById("carrinho-modal-list").innerHTML = ``;
			itemLista.id = "item-carrinho-" + produto.id;
			itemLista.classList.add('list-group-item');
			itemLista.innerHTML = `
    <div class = "row" style="text-align:left;">
    	<div class  = "col-6">
      		<img src="img/prodImages/${produto.img}.jpg"  style = "width:100px;height:100px; object-fit:contain;margin:auto;"alt="${produto.nome}">
     	 </div>
      	 <div class = "col-6">
      		<h5>${produto.nome} <span id="produto-carrinho-${produto.id}"class="badge badge-primary badge-pill">${produto.quantidade}</span></h5>
      		<p>R$ ${produto.preco.toFixed(2)}</p>
      		<div class = "row justify-content-around">
      			<button class = "btn  btn-sm btn-light" onclick ="aumentarQuantidade(${produto.id}),mostraCarrinho()"><i class="fas fa-plus"></i></button>
      			<button class = "btn  btn-sm btn-light" onclick ="diminuirQuantidade(${produto.id}),mostraCarrinho()"><i class="fas fa-minus"></i></button>
      		</div>
      	</div>
    </div>
  `;
			listaProdutos.appendChild(itemLista);
			totalCarrinho += produto.preco * produto.quantidade;
			document.getElementById("botao-fechar-compra").style.display = "block";
		}
		document.getElementById("total-carrinho").innerHTML = "Sub-total: R$" + totalCarrinho;
	}
}

function fecharCompra() {
	let carrinho = localStorage.getItem('carrinho');
	if (carrinho != null) {
		localStorage.removeItem('carrinho');
		$.ajax({
			type: "POST",
			url: "/fecharCompra",
			contentType: "application/json",
			data: carrinho,
		});
	}
}
function aumentarQuantidade(idProduto) {
	let carrinho = localStorage.getItem('carrinho');
	carrinho = JSON.parse(carrinho);
	for (i = 0; i < carrinho.length; i++) {
		if (carrinho[i].id == idProduto) {
			carrinho[i].quantidade += 1;
			document.getElementById("produto-carrinho-" + idProduto).innerHTML = carrinho[i].quantidade;
			localStorage.setItem('carrinho', JSON.stringify(carrinho));
			totalCarrinho += carrinho[i].preco;
			document.getElementById("total-carrinho").innerHTML = "Subtotal R$" + totalCarrinho;
			return;
		}
	}
}

function diminuirQuantidade(idProduto) {
	let carrinho = localStorage.getItem('carrinho');
	carrinho = JSON.parse(carrinho);
	for (i = 0; i < carrinho.length; i++) {
		if (carrinho[i].id == idProduto) {
			carrinho[i].quantidade -= 1;
			if (carrinho[i].quantidade == 0) {
				let listaProdutos = document.getElementById("lista-produtos");
				let itemRemovido = document.getElementById("item-carrinho-" + idProduto);
				listaProdutos.removeChild(itemRemovido);
				totalCarrinho -= carrinho[i].preco;
				carrinho.splice(i, 1);
				localStorage.setItem('carrinho', JSON.stringify(carrinho));
				carregaCarrinho();
				document.getElementById("total-carrinho").innerHTML = "Sub-total: R$" + totalCarrinho;
				return;
			}
			document.getElementById("produto-carrinho-" + idProduto).innerHTML = carrinho[i].quantidade;
			localStorage.setItem('carrinho', JSON.stringify(carrinho));
			totalCarrinho -= carrinho[i].preco;
			document.getElementById("total-carrinho").innerHTML = "Sub-total: R$" + totalCarrinho;
			return;
		}
	}
}

function carregaCarrinho() {
	let carrinho = localStorage.getItem('carrinho');
	if (carrinho != null) {
		carrinho = JSON.parse(carrinho);
		document.getElementById("numero-produtos").innerHTML = carrinho.length;
		return;
	}

	document.getElementById("numero-produtos").innerHTML = 0;
}

function adicionarCarrinho(id, name, img, preco, quantidade) {
	let carrinho = localStorage.getItem('carrinho');
	if (carrinho == null) {
		carrinho = [{ id: id, nome: name, img: img, preco: preco, quantidade: quantidade }]
		document.getElementById("numero-produtos").innerHTML = 1;
		localStorage.setItem('carrinho', JSON.stringify(carrinho));
		return;
	}
	carrinho = JSON.parse(carrinho);
	document.getElementById("numero-produtos").innerHTML = carrinho.length;
	for (i = 0; i < carrinho.length; i++) {
		if (carrinho[i].id == id) {
			carrinho[i].quantidade += 1;
			localStorage.setItem('carrinho', JSON.stringify(carrinho));
			return;
		}

	}
	let novoProduto = { id: id, nome: name, img: img, preco: preco, quantidade: quantidade };
	carrinho.push(novoProduto);
	document.getElementById("numero-produtos").innerHTML = carrinho.length;
	localStorage.setItem('carrinho', JSON.stringify(carrinho));
}

function mostraCarrinho() {
	let carrinho = localStorage.getItem('carrinho');
	totalCarrinho = 0;
	if (carrinho != null) {
		carrinho = JSON.parse(carrinho);
		var listaProdutos = document.getElementById("lista-produtos");
		listaProdutos.innerHTML = "";
		if (carrinho.length == 0) {
			document.getElementById("carrinho-modal-list").innerHTML = `
					<div class = "text-center">
						<h5>Nada por aqui ainda...</h5>
					</div>`;
			document.getElementById("total-carrinho").innerHTML = "";
			document.getElementById("botao-fechar-compra").style.display = "none";
			return;
		}
		for (var i = 0; i < carrinho.length; i++) {
			var produto = carrinho[i];
			var itemLista = document.createElement('li');
			document.getElementById("carrinho-modal-list").innerHTML = ``;
			itemLista.id = "item-carrinho-" + produto.id;
			itemLista.classList.add('list-group-item');
			itemLista.innerHTML = `
    <div class = "row" style="text-align:left;">
    	<div class  = "col-6">
      		<img src="img/prodImages/${produto.img}.jpg"  style = "width:100px;height:100px; object-fit:contain;margin:auto;"alt="${produto.nome}">
     	 </div>
      	 <div class = "col-6">
      		<h5>${produto.nome} <span id="produto-carrinho-${produto.id}"class="badge badge-primary badge-pill">${produto.quantidade}</span></h5>
      		<p>R$ ${produto.preco.toFixed(2)}</p>
      		<div class = "row justify-content-around">
      			<button class = "btn  btn-sm btn-light" onclick ="aumentarQuantidade(${produto.id}),mostraCarrinho()"><i class="fas fa-plus"></i></button>
      			<button class = "btn  btn-sm btn-light" onclick ="diminuirQuantidade(${produto.id}),mostraCarrinho()"><i class="fas fa-minus"></i></button>
      		</div>
      	</div>
    </div>
  `;
			listaProdutos.appendChild(itemLista);
			totalCarrinho += produto.preco * produto.quantidade;
			document.getElementById("botao-fechar-compra").style.display = "block";
		}
		document.getElementById("total-carrinho").innerHTML = "Sub-total: R$" + totalCarrinho;
	}
}