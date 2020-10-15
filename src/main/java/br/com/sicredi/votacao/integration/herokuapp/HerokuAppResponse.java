package br.com.sicredi.votacao.integration.herokuapp;

public class HerokuAppResponse {
    private String status;

    public HerokuAppResponse() {
    }

    public HerokuAppResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
