import static com.lybb.ms.utils.MD5Util.inputPassToDbPass;
import static com.lybb.ms.utils.MD5Util.inputPassToFormPass;

import com.lybb.ms.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/25 9:14 $
*/

@SpringBootTest(classes = {com.lybb.ms.utils.MD5Util.class})
public class Md5UtilTest {

    @Test
    public void getPass(){
        System.out.println(inputPassToDbPass("111111", "66abc9"));
    }

    @Test
    public void inputPass(){
        //d018506bc314a32b93eb214102399a63
        System.out.println(inputPassToFormPass("111111"));
    }

}
