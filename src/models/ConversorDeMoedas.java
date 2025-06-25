package models;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorDeMoedas {

    private int opcoesDeConversao;
    private String mensagemDeBoasVindas;
    private String mensagemDeSaida;
    @SerializedName("base_code")
    private String opcapDaApiDe;
    @SerializedName("target_code")
    private String opcapDaApiPara;
    @SerializedName("conversion_rate")
    private double valor;
    @SerializedName("conversion_result")
    private double resultado;

    public void exibeMensagemBoasVindas() {
        mensagemDeBoasVindas = """
                ~ Bem vindo ao conversor de moedas v1! ~
                1 - Para converter Real Para Dólar.
                2 - Para converter Dólar para Real.
                3 - Para converter Real Para Euro.
                4 - Para converter Euro Para Real.
                5 - Para converter Real Para Iene.
                6 - Para Converter Iene Para Real.
                """;
        System.out.println(mensagemDeBoasVindas);
    }

    public void mensagemValor() {
        System.out.println("Qual o valor a ser convertido?");
    }

    public int opcaoMenu(int opcao) {
        return opcoesDeConversao = opcao;
    }

    public double recebeValor(double quantidade) {
        return this.valor = quantidade;
    }

    public void chamaApi() throws IOException, InterruptedException {

        if (opcoesDeConversao == 1) {
            opcapDaApiDe = "BRL";
            opcapDaApiPara = "USD";
        } else if (opcoesDeConversao == 2) {
            opcapDaApiDe = "USD";
            opcapDaApiPara = "BRL";
        } else if (opcoesDeConversao == 3) {
            opcapDaApiDe = "BRL";
            opcapDaApiPara = "EUR";
        } else if (opcoesDeConversao == 4) {
            opcapDaApiDe = "EUR";
            opcapDaApiPara = "BRL";
        } else if (opcoesDeConversao == 5) {
            opcapDaApiDe = "BRL";
            opcapDaApiPara = "JPY";
        } else if (opcoesDeConversao == 6) {
            opcapDaApiDe = "JPY";
            opcapDaApiPara = "BRL";
        }

        recebeValor(valor);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/72d5fae063ab21acd1604fda/pair/" + opcapDaApiDe + "/" + opcapDaApiPara + "/" + valor))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        ConversorDeMoedas variavel = gson.fromJson(json, ConversorDeMoedas.class);
        System.out.println(variavel);

    }

    public void sair() {
        System.out.println("Deseja continuar?");
    }

    public void opcaoDeSaida(String msg) {

        this.mensagemDeSaida = msg;
        if (this.mensagemDeSaida.equalsIgnoreCase("Sim") || this.mensagemDeSaida.equalsIgnoreCase("s")) {
            System.out.println("Vamos para a próxima conversão.............\n");
        } else {
            System.exit(0);
        }

    }

    @Override
    public String toString() {
        return """ 
                   ~ Resultado da Conversão ~
                
                      ~ %s para %s. ~
                
                         ~ %.2f ~
                
                """.formatted(opcapDaApiDe, opcapDaApiPara, resultado);
    }
}