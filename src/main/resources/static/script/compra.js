
function gerarCompras() {
	let carrinho = localStorage.getItem('carrinho');
	document.getElementById("carrinho-icon").style.display = "none";
	let totalCompra = 0;
	if (carrinho != null) {
		carrinho = JSON.parse(carrinho);
		var li = document.getElementById("lista-compras");
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
      		<h5>${produto.nome}</h5>
      		<p>R$ ${produto.preco.toFixed(2)}</p>
      		<div class = "row">
      			<button class = "mr-2 btn  btn-sm btn-light" onclick ="aumentarQuantidadeCompra(${produto.id})"><i class="fas fa-plus"></i></button>
      			<div><span id="produto-carrinho-${produto.id}"class="badge badge-light" style="font-size:20px;">${produto.quantidade}</span></div>
      			<button class = "ml-2 btn  btn-sm btn-light" onclick ="diminuirQuantidadeCompra(${produto.id})"><i class="fas fa-minus"></i></button>
      		</div>
      	</div>
    </div>
  `;
			li.appendChild(itemLista);
			totalCompra += parseFloat(produto.preco * produto.quantidade);
		}
		document.getElementById("total-venda").innerHTML = "Total: R$" + totalCompra;
	}
}
function aumentarQuantidadeCompra(idProduto) {
	let carrinho = localStorage.getItem('carrinho');
	carrinho = JSON.parse(carrinho);
	index = carrinho.findIndex(item => item.id == idProduto);
	if (index === -1) return;
	carrinho[index].quantidade++;
	localStorage.setItem('carrinho', JSON.stringify(carrinho));
	gerarCompras();
}
function diminuirQuantidadeCompra(idProduto) {
	let carrinho = localStorage.getItem('carrinho');
	carrinho = JSON.parse(carrinho);
	index = carrinho.findIndex(item => item.id == idProduto);
	if (index === -1) return;
	carrinho[index].quantidade--;
	if (carrinho[index].quantidade == 0) {
		carrinho.splice(index, 1);
	}
	localStorage.setItem('carrinho', JSON.stringify(carrinho));
	gerarCompras();
	if (carrinho.length == 0) {
		localStorage.removeItem('carrinho');
		return;
	}
}