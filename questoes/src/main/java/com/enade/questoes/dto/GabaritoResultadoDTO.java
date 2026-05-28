package com.enade.questoes.dto;

public class GabaritoResultadoDTO {
    private boolean correto;
    private String letraCorreta;
    private String GabaritoComentado;
    
    
    public GabaritoResultadoDTO(boolean correto, String letraCorreta, String gabaritoComentado) {
        this.correto = correto;
        this.letraCorreta = letraCorreta;
        GabaritoComentado = gabaritoComentado;
    }


    public boolean isCorreto() {
        return correto;
    }


    public void setCorreto(boolean correto) {
        this.correto = correto;
    }


    public String getLetraCorreta() {
        return letraCorreta;
    }


    public void setLetraCorreta(String letraCorreta) {
        this.letraCorreta = letraCorreta;
    }


    public String getGabaritoComentado() {
        return GabaritoComentado;
    }


    public void setGabaritoComentado(String gabaritoComentado) {
        GabaritoComentado = gabaritoComentado;
    }

    
}
