package other;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.Set;

public class CharsetDemo {



    @Test
    public void demo01() throws CharacterCodingException {
        //1、获取 charset 对象
        Charset charset = Charset.forName("utf-8");
        //2、获得编码对象
        CharsetEncoder encoder = charset.newEncoder();

        //3、创建缓冲区
        CharBuffer buffer = CharBuffer.allocate(1024);
        buffer.put("information消息");
        buffer.flip();

        //4、编码
        ByteBuffer byteBuffer = encoder.encode(buffer);
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.print(byteBuffer.get()+"\t");
        }

        //5、获取解码对象
        byteBuffer.flip();
        CharBuffer decodeBuffer = charset.decode(byteBuffer);
        //6、解码
        System.out.println("解码：");
        System.out.println(decodeBuffer.toString());

        //7 使用 gbk 编码
        System.out.println("指定其他格式解码:");
        Charset charset1=Charset.forName("GBK");
        byteBuffer.flip();
        CharBuffer decodeBuffer2 =charset1.decode(byteBuffer);
        System.out.println(decodeBuffer2.toString());

        //获取 Charset 所支持的字符编码
        Map<String ,Charset> map= Charset.availableCharsets();
        Set<Map.Entry<String,Charset>> set=map.entrySet();
        for (Map.Entry<String,Charset> entry: set) {
            System.out.println(entry.getKey()+"="+entry.getValue().toString());
        }
    }





}
