function gerarCompras() {
	let carrinho = localStorage.getItem('carrinho');
	document.getElementById("carrinho-icon").style.display = "none";
	totalCarrinho = 0;
	if (carrinho != null) {
		carrinho = JSON.parse(carrinho);
		var li = document.getElementById("lista-compra");
		li.innerHTML = "";
		for (var i = 0; i < carrinho.length; i++) {
			var produto = carrinho[i];
			var itemLista = document.createElement('li');
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
      			<button class = "btn  btn-sm btn-light" onclick ="aumentarQuantidade(${produto.id}),mostraCarrinho(),gerarCompras()"><i class="fas fa-plus"></i></button>
      			<button class = "btn  btn-sm btn-light" onclick ="diminuirQuantidade(${produto.id}),mostraCarrinho(),gerarCompras()"><i class="fas fa-minus"></i></button>
      		</div>
      	</div>
    </div>
  `;
			li.appendChild(itemLista);
			totalCarrinho += produto.preco * produto.quantidade;
		}
		document.getElementById("total-venda").innerHTML = "Total: R$" + totalCarrinho;
	}
}