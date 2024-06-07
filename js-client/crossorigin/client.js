function consultarRestaurantes() {
    $.ajax({
        url: "http://localhost:8080/restaurantes",
        type: "get",
        headers: {
            "X-Test": "ABC"
        },

        success: function (response) {
            $("#conteudo").text(JSON.stringify(response));
        }
    });
}

function consultarCozinhas() {
    $.ajax({
        url: "http://localhost:8080/cozinhas",
        type: "get",
        headers: {
            "X-Test": "ABC"
        },

        success: function (response) {
            $("#conteudo").text(JSON.stringify(response));
        }
    });
}

function fecharRestaurante() {
    $.ajax({
        url: "http://localhost:8080/restaurantes/1/fechamento",
        type: "put",

        success: function (response) {
            alert("Restaurante foi fechado")
        }
    });
}

$("#listar-restaurante-id").click(consultarRestaurantes);
$("#listar-cozinhas-id").click(consultarCozinhas);
$("#fechar-restaurante-id").click(fecharRestaurante);
