import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author: LaoAyu
 * @date: 2023/03/07
 **/
public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/parking?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        String username = "root";
        String password = "123456";
        String moduleName = "system";
        String tables = "car_scan";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("laoayu") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\桌面"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.laoayu.parking") // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\ParkingManagementSystem\\Parking\\parking-admin\\src\\main\\resources\\mapper\\" + moduleName)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix("sys_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
