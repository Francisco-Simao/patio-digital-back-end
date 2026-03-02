package br.prf.patio_digital.exception;

public class ErrorResponse {
    private String erro;
    private int codigo;

    public ErrorResponse(String erro, int codigo) {
        this.erro = erro;
        this.codigo = codigo;
    }

    public String getErro() {
        return erro;
    }

    public int getCodigo() {
        return codigo;
    }
}
