package com.rocky.sorm.utils;

import com.rocky.sorm.bean.ColumnInfo;
import com.rocky.sorm.bean.JavaFieldGetSet;
import com.rocky.sorm.bean.TableInfo;
import com.rocky.sorm.core.DBManager;
import com.rocky.sorm.core.TypeConverter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaFileUtils {

    public static JavaFieldGetSet createFiledGetSetSRC(ColumnInfo columnInfo, TypeConverter converter) {

        JavaFieldGetSet javaFieldGetSet = new JavaFieldGetSet();

        String javaFieldType = columnInfo.getDataType();

        /**
         * private String name;
         */
        String filedSrc = "\tprivate " + converter.databaseType2JavaType(javaFieldType) + " " + columnInfo.getName() + ";\n";

        /**
         * public String getName(){
         *     return name;
         * }
         */
        StringBuilder getSrc = new StringBuilder();
        getSrc.append("\tpublic " + converter.databaseType2JavaType(javaFieldType)
                + " get" + StringUtils.firstChar2UpperCase(columnInfo.getName()) + "(){\n");
        getSrc.append("\t\treturn " + columnInfo.getName() + ";\n");
        getSrc.append("\t}\n");


        /**
         * public void setName(String name){
         *     this.name = name;
         * }
         */
        StringBuilder setSrc = new StringBuilder();
        setSrc.append("\tpublic " + "void"
                + " set" + StringUtils.firstChar2UpperCase(columnInfo.getName()) + "("+ converter.databaseType2JavaType(javaFieldType) + " " + columnInfo.getName() + "){\n");
        setSrc.append("\t\tthis." + columnInfo.getName() + "=" + columnInfo.getName() + ";\n");
        setSrc.append("\t}\n");

        javaFieldGetSet.setFieldInfo(filedSrc);
        javaFieldGetSet.setGetInfo(getSrc.toString());
        javaFieldGetSet.setSetInfo(setSrc.toString());

        return javaFieldGetSet;
    }

    /**
     * 根据表信息生成java类的源代码
     * @param tableInfo 表信息
     * @param converter 数据类型转化器
     * @return java类的源代码
     */
    public static String createJavaSrc(TableInfo tableInfo, TypeConverter converter) {
        Map<String,ColumnInfo> columns = tableInfo.getColumns();
        List<JavaFieldGetSet> javaFields = new ArrayList<JavaFieldGetSet>();

        for(ColumnInfo c:columns.values()){
            javaFields.add(createFiledGetSetSRC(c, converter));
        }

        StringBuilder src = new StringBuilder();

        //package com.rocky.po
        src.append("package "+ DBManager.getConf().getPoPackage() + ";\n\n");

        //import java.sql.*
        //import java.util.*
        src.append("import java.sql.*;\n");
        src.append("import java.util.*;\n\n");

        //public class Emp{
        src.append("public class " + StringUtils.firstChar2UpperCase(tableInfo.getTname() + "{\n"));

        //生成属性列表
        for(JavaFieldGetSet f:javaFields){
            src.append(f.getFieldInfo());
        }
        src.append("\n\n");
        //生成get方法列表
        for(JavaFieldGetSet f:javaFields){
            src.append(f.getGetInfo());
        }
        //生成set方法列表
        for(JavaFieldGetSet f:javaFields){
            src.append(f.getSetInfo());
        }

        //生成类结束
        src.append("}\n");
        return src.toString();
    }

    /**
     *
     * @param tableInfo
     * @param converter
     */
    public static void createJavaPOFile(TableInfo tableInfo, TypeConverter converter) {
        String src = createJavaSrc(tableInfo, converter);

        String srcPath = DBManager.getConf().getSrcPath();
        String poPackagePath = DBManager.getConf().getPoPackage().replace(".", "/");
        File file = new File(srcPath + poPackagePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile() + "/"
                    + StringUtils.firstChar2UpperCase(tableInfo.getTname()) + ".java"));
            writer.write(src);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
