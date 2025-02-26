package com.example.demo.services;

import com.example.demo.entities.Lanche;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class LancheService {
    private String filePath = "C:\\Users\\aluno.fsa\\ImagensLancheDestino\\";

    public Lanche getById(int id) {
        return null;
    }

    public boolean exists(int id) {
        return true;
    }

    private String getFileExtension(Path path) {
        String filename = path.getFileName().toString();
        int lastDotIndex = filename.lastIndexOf('.');

        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return "";
        }

        return filename.substring(lastDotIndex + 1);
    }

    public boolean salvar(Lanche lanche) {
        Path path = Paths.get(lanche.getImagem());

        Path destinationPath = Paths.get(String.format("%s%d.%s", filePath, lanche.getCodigo(), getFileExtension(path)));

        if (Files.exists(path)) {
            try {
                Files.copy(path, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                lanche.setImagem(destinationPath.toString());
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        return false;
    }

    public void excluir(int id, Lanche Lanche) {

    }

    public void atualizar(int id, Lanche Lanche) {

    }
}
