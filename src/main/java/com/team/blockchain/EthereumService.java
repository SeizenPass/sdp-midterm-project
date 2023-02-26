package com.team.blockchain;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;

@Getter
public class EthereumService {
    @Getter
    private static String folder;
    @Getter
    private static final String SimpleWalletBinary = "SimpleWallet.bin";
    public static void loadVariables(Dotenv dotenv) {
        folder = dotenv.get("BINARY_FOLDER");
    }
}
