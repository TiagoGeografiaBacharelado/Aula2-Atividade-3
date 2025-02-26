package com.example.demo.facade;

import com.example.demo.applications.LancheApplication;
import com.example.demo.entities.Lanche;
import com.example.demo.repositories.LancheRepository;

import java.util.List;

public class LancheFacade {
    private LancheApplication lancheApplication;

    public LancheFacade(LancheApplication lancheApplication) {
        this.lancheApplication = lancheApplication;
    }

    public void cadastrar(Lanche lanche) {
        this.lancheApplication.cadastrar(lanche);
    }

    public List<Lanche> buscar() {
        return this.lancheApplication.buscar();
    }

    public Lanche buscarPorCodigo(int codigo) {
        return this.lancheApplication.buscarPorCodigo(codigo);
    }

    public double calcularLanche(Lanche lanche, int quantidade) {
        return this.lancheApplication.calcularLanche(lanche, quantidade);
    }
}
