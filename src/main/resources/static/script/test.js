var chai = window.chai;
var assert = chai.assert;

describe('adicionarCarrinho', function(){
    it('deveria adicionar um produto ao carrinho', function(){
        localStorage.clear();
        adicionarCarrinho(Number(1),"nome produto","imagem",Number(2),Number(1));
        assert.equal(JSON.parse(localStorage.getItem('carrinho')).length , 1);
        localStorage.clear();
    });
    it('deveria adicionar um produto ao carrinho', function(){
        localStorage.clear();
        adicionarCarrinho(Number(1),"nome produto","imagem",Number(2),Number(1));
        adicionarCarrinho(Number(1),"nome produto","imagem",Number(2),Number(1));
        assert.equal(JSON.parse(localStorage.getItem('carrinho')).length , 1);
        localStorage.clear();
    });
    it('deveria adicionar um produto ao carrinho', function(){
        localStorage.clear();
        adicionarCarrinho(Number(1),"nome produto","imagem",Number(2),Number(1));
        adicionarCarrinho(Number(2),"nome produto","imagem",Number(2),Number(1));
        assert.equal(JSON.parse(localStorage.getItem('carrinho')).length , 2);
        localStorage.clear();
    });
})
