package com.blockchainspace.ecommerce;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;

public class CodeGenerator {

    public static void main(String[] args) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/blockchainspace","root","toor").build();
        String projectPath = System.getProperty("user.dir");
        GlobalConfig globalConfig = new GlobalConfig.Builder().outputDir(projectPath+"/src/main/java")
                .author("Ichroman Raditya Duwila")
                .disableOpenDir()
                .build();
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("com.blockchainspace.ecommerce")
                .mapper("persistence.mapper")
                .entity("persistence")
                .build();
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
        autoGenerator.global(globalConfig).packageInfo(packageConfig);
        autoGenerator.execute();
    }

}
