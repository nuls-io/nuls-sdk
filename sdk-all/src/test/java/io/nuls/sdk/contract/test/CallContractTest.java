package io.nuls.sdk.contract.test;

import io.nuls.sdk.contract.service.ContractService;
import io.nuls.sdk.contract.service.UTXOService;
import io.nuls.sdk.contract.service.impl.ContractServiceImpl;
import io.nuls.sdk.contract.service.impl.UTXOServiceImpl;
import io.nuls.sdk.core.SDKBootstrap;
import io.nuls.sdk.core.model.Na;
import io.nuls.sdk.core.model.Result;
import io.nuls.sdk.tool.NulsSDKTool;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by wangkun23 on 2018/11/13.
 */
public class CallContractTest {

//    final Logger logger = LoggerFactory.getLogger(CallContractTest.class);
//
//    @Before
//    public void init() {
//        SDKBootstrap.init("192.168.1.133", "8001");
//    }
//
//    @Test
//    public void callContractTransaction() {
//        String sender = "Nsdv1Hbu4TokdgbXreypXmVttYKdPT1g";
//        String contractAddress = "NseMGKKGooiKdzEAT3UvwPYTshebUfhX";
//        ContractService contractService = ContractServiceImpl.getInstance();
//        UTXOService utxoService = UTXOServiceImpl.getInstance();
//
//        Long value = 100_0000_0000L;
//        Long gasLimit = 81325l;
//        Long price = 25L;
//        String methodName = "create";
//        String methodDesc = "";
//        Object[] args = {"test", "test desc", Arrays.asList("1", "2", "3"), "1542042000000", "1542646800000", "false", "1", "1", "false"};
//        String remark = "test call contract";
//        Result result = contractService.callContractTransaction(sender,
//                value, gasLimit, price,
//                contractAddress,
//                methodName, methodDesc, args,
//                remark,
//                utxoService.getUTXOs(sender, 150_0000_0000L));
//
//        logger.info("callContractTransaction {}", result);
//        Map<String, Object> map = (Map<String, Object>) result.getData();
//        String txHex = (String) map.get("value");
//        logger.info("txHex {}", txHex);
//        String priKey = "00ef8a6f90d707ab345740f0fab2d9f606165209ce41a71199f679f5dfd20bfd1d";
//        result = NulsSDKTool.signTransaction(txHex, priKey, sender, null);
//        logger.info("signature result {}", result);
//    }
//
//    @Test
//    public void broadcastTransaction() {
//        String txHex = "64008df4b40b6701000423011eaa95ce055cef73615af36c71bdd3d278c4a28404230252f65d5d86d4c952427a6c51ecb59fea8ee6fcf200000000000000009b420000fd9b42504b0304140008080800fa84354d000000000000000000000000140004004d4554412d494e462f4d414e49464553542e4d46feca0000f34dcccb4c4b2d2ed10d4b2d2acecccfb35230d433e0e572ce492c2ed60d482cc9b05248cecf2b294a4c2ed12d4ec9d635d403caeb652516f172f1720100504b0708764604263e0000003d000000504b03040a0000080000fa84354d00000000000000000000000003000000696f2f504b03040a0000080000fa84354d00000000000000000000000008000000696f2f6e756c732f504b03040a0000080000fa84354d0000000000000000000000000d000000696f2f6e756c732f766f74652f504b03040a0000080000fa84354d00000000000000000000000016000000696f2f6e756c732f766f74652f636f6e74726163742f504b0304140008080800f984354d00000000000000000000000028000000696f2f6e756c732f766f74652f636f6e74726163742f566f7465436f6e74726163742e636c6173739d55eb56135714fe26844c48065034b4b155f0521b2e3aadb5b40a22906a4d1ad402a6057a3b4c4ec2c1c90cce05c567ea0fbb562b52d7ea03f449fa145ddd7b12239208d8c5e2cc3ee7ecfded6f5fcecedffffef917802b789a4206251df329dcc5bd1462b8df8393f8368d052cea584a41c78314caf82e85efb1ccc72b49aca6f0037ee4cd4fbcfccccb2fbc085ed678b1745474481d550dc935e1cbb21b480da325e59a4e68fbe616ed4dcb75024f5881590d1dcb6495821348af2a2c39a92131a51c154c6b88e78a2365fae4dd0a61f4979423ef86f535e92d89359b4e064aae25ecb2f014ef9b87f1605df91a2ebcc5233bcb3737e4abbfae9c0569b935473d158e45f65a9118589e14ccbb9a2b6d882d61dac2a9998b81a79cda64fbc96afb51b1b8525819790b873ac563474c6e39810ab68947377d1b21b541514815e95ba4a30259a7d04e74f0a7a1c70f84172ca93aa1e8d2a934a463ca9f0fed406dda7251dad20a28c0150d7d75f1a4b1cfbba1c387050dbdcc322f9c79b7a2aadb1a525b2d861a72478f25419755553b8a4d3ed2241bdd0f2d4bfa145d76810811f7b2f2151574d671dc4004ca75e8ee75425b587ee5a1295a3ae67db1cd6dc049db8a3a2f912bae164728e404ef0b1572c5692c54082eb64ab54e79b222eb9b6cdde838d2d52de1341af7e2a11ecb4a3ee6f43f0aa5b7dd301a2394a3a7abbf65b9207daa153501db47250e03659bf362931d2caa9a2382d023074b6d0a539d79ce562a1e6575728f7249f9c1d49e062ab9d43ed3f4478d153169dadc117e239a73b9e2c1e051c64463a3e1ccc1ca94f04537f42c795b71bf1fdffb1e2f332b035950ef9c3f6060cc35e78a8e75030a1b6cf2d0c07b785f876de01cea54b8a3b51e9b3a3a5c039b78a4c333e0233010628b1e099b0ef32c1aae0a65eb786ce009b60d7c812f0d4ce18681695e66306b600e7903b7f135e5ebf0c143eff27501eead6d44eff274c7bcbd36e9caf1303c1919d645b06ecea91a8fcd9af428ff5bc20ee53d9abad9566fec539ae4bbce370c9c3bea84d620feef547c875791cc354668f908a57c35a3ba6b32e0173e901bd9dfe054cd374fe841d9f42973daa2dcd2288873ad358cef69f8430617b7fea98e757b406f8d5e83eec947a1e2373b985be9909032ced2ef6e06744fff31ee61fa814e929cc5295a3fa0dd7d74a38bbe274777a08dbe446c79e805ba7610ff03ddbfd1790c1fd2da17e9641027a41384739a7683744776388321209286c91f4d7b7a23e7e98ed1ff410fd90233a3bfa37b6cfcd22e12715c4fbf84be3c181f4c6492999e4c6a07c9eb062b64d3cfd1f31ca9acb18b741c13bd99de01e3057ab3e9672d2683c41784dd471cce92ef09f27a833c32a3a730d8578bd10c2ee023e2c3d245926291f4317251343318218478248d929488a4318c538658ba84cbc49e2593ce52e4f10a3ec1a74893ee10c99f91b7093abf4ab7bdf89c3413e46b82be1a3fe2667e8be4817d44010e5102fae2f8b5154d22623716b11f8e58192df606ae45f964e93a26e956e3b9d0c4bd497bd64f47b8bbe8ef6a8335f79429dd824d3761359e2fcd32ed073bd60e76f540b057d1df8ca2a799f516e0e35d78b60ff8da3b00d31c24b84ec003edc0d307027f1569dfdae780666c93795bdd4eb4d72d7f68ddeea040b76ffa284628dffc07504b0708ea1b2f50bf0400002f0b0000504b03040a0000080000fa84354d0000000000000000000000001c000000696f2f6e756c732f766f74652f636f6e74726163742f6d6f64656c2f504b0304140008080800f984354d0000000000000000000000002a000000696f2f6e756c732f766f74652f636f6e74726163742f6d6f64656c2f566f74654974656d2e636c6173738d935b4f135110c7ffa7b72debb640b90a72b12896b6b88277a988e00dad9a8821d12796b281d5a58bdd2d2fc68fe017f05d79e141132d8926c61731f133197566778b652989693ae79c3933677ee73f677ffefefc15c0248a325238d78228ceb3b9c0cb8b122ec9b88c2b32a6d85c46816705095765c83817c7348fd7d8ccc4713d8e599ece49b82110325604da8acfb44d4d35b5f2aa5ab4caab530252c92a3b7ad91148356c2e3815c3dd8e158cb2e14c0b8433638b0291396b4517682d1a65fd41757d59af3cd6964d9d93ad92662e6a1583d7be33e2ac19b6c068d1b0d472d5b4d54dcbd1552e58d14a8eba4e6799ea22f9e61d7d9d8a455775679e305399b183a051dbdbecc804f7184ca6d4b9fa4d3af7e5efdd45b61b62ba320743f8a098fea2aa997620e0e1f233bde44c8d3d1510d67ea9fc2d81f8a67f1381c482a3959edfd7365c21a86db4bba6d96b9e7a24e53c15aae876d52412418bb863790c44b960552b25fd96c10a26eaea9ce6820a7ad0aba0039d0abad0ade0266e099cf81f7525dc56308a5312ee2818c769819ee0f567ab86b9a257e849bc4c1b2be92bc30ae67197cd3d81647e38edbf14da49d37b4abf523081117a54412de83adc0d53b36d6e57632f5c276995dcdfc27d87d48588691b1b7a993a3edeac55075c3efe5420bedeb943e3719cbeaf147d7421fa91b4ee8cd4a5310ec18a933d4a2b9546416334bb03f1c10deb231b739d61f49355bc001cc3008d0283183a90fc11a1f781e496a6c9c344e6254fbb6c149dcd7d42e85f69d9f5262827e99ed0ed45f927f08cefc385d3186902120e82b43705398193cd40c241902ecae93e048405e5c2f404fdb3be534c98c6a56cee1da291eddc2e92d91a2239fabf4534bc9dfb86e87d966b179d3ce4e95f43ec0d5ab720e75d3f45f13576d1ce03f9c235485b68e5d90f48912d44c2db6e77fa5caa28d93e48c4d84b6403d49d69929989873d963de225643046a4039843966c0839579f1629297ee198847c4c74ca337c25fa9afc2bbdf6e519f7a813599738ce1485b6a1fe358f95ddc4d9c2eea542ff3fbea4cb70929a364ad54e352839ee7375509c8a33548e690611699f117f48db10f1100acde1cf4382d126f6dafe88cee1ae66be407eb2832329a556e74ba65a6b75a844aa8d4c0dedc197916f781999bd9731e9469dfd0b504b07085259657c57030000b6060000504b0304140008080800f984354d0000000000000000000000002c000000696f2f6e756c732f766f74652f636f6e74726163742f6d6f64656c2f566f7465456e746974792e636c6173739557eb73135514ff6d5ebb49435bda525a0bd25684901602880ab460a18044cab358015f2cc9926e499392ddf014ebfb8d6f7ca0a3e307e50ba3322361d419c74f38e317ff15bff9c551cfd9bbbb4db69b52679a7bcf9efb3bf7fef677ce3d497fffe7a75f00acc7e731acc464140d28f050e4618a87d33c9478307830792833f88c8cb3319cc3f9182ef07091876778b884f30d7896ad7398e6d5695e9de6d5695e9de6d56919cfc5d0814905cff3fc020f2f2a7849c1cb0a5e51f02a3b5e53f0ba823714bcc94f6ff17059c6db12027a5642f3c8847a464de5d5422e35522ce40624844dddcc6b125aaa9646cd926e2d86b29a919110c9140b27f59c84c4885e4c15ca792375a6686a29729b253563a6268b592d9f1a23dfb085a4d08861aa66d99020a5e990e2d982569270b71bef861ad953a96dd96c49330c8a8a97b44c3157d02fa8850c916a17a42655733cb55dcfa50ba696d34acc5a37b549c37da1b2a9e75323ba61d2527454cf15e8e812c56ff42c0fde917f9af61dd8cafc07f5826e6e95104cac1a2325860922a169442f68fbca9327b4d261f584d0ad9851f3636a49e767db1932c775633e72ed2c90fce7f985729a99a60cb52456f9e4c8108bad09ef1a535328f4b048625b4db49b46c570118b12b301bc894c9becb0922d1b8e1525dfb09df924ed3cefdc478d99c0be79d78cf32e69915a5789eae46e9ee5fc1f296519eccd1d2567f6e6d3db6b73797ecac9e780173eef53795bd671d4be0d544d692190e30925d2ce9bef17b7a4bb4aeb3af7845fc546f724e606f3e64db4f9a19a9bd5e10839fb6e35195e7067c21fcb5b47b4d365356f782a6bff89092d43a21ea3fb5fac6d2ef69275495453c2021222736aaf3a65694d3d92de6e5c35c6c5858bd03b94f30453cca2285709b1d162b994d176e99c9aa6996bb4860f89631336c791c0aa3892e88ba39f87d55813c7fd78208e14d6c6b10eebe3b80f1be27807ef4a5831bf6b2ae3bd38549c90f1be8c0f647c28e38a8c8fe2180709b1d87bafb697f57c9633245fecd5b3bd9bbbe3f8189ff0f02935bafeee5eabf392bf971e7be9999bad785cc08fa2e99243a0453fb5b7b92a7c565b1521ad1c52dd3dadc006725aadd28efb2c8e09eca4bee9cd8728c0e1bc6a58b7a3ba8d584eca57636defa9d9c449cd1d9574dac292b96a961a995fbd3914dc5b48e5a14e4d69056a8dabfdfada2c979d92010fdea9d639f04be896ceb93c5c7f193df4136025fd6a5010e0c2242bc0b569cdfdf64c156acd549fd64c256acd54a5d64cc50b090f5af646b4914d754ee3007952344b348793b720ddb0208334462ca7822d34c605005bf110cd1286b06d56f00f087cef096ef30dde8e613b782ba1038c4ef6dd4460e6e898e5eda0984e6b877681b277608b75e0837760a70f91a097c8125f22bbf0b01f91a0974837c5f4d421c289e08377fb120979892cf72592f62712f2124950ccaa3a44fa6d228f608f0f91b09748bf2f9111ecf52312f612594b31ebea10e152e483f7613fad798944bc4436f81011757a807c0771c8dee44035a108130a5611da48b19bea1012b53fea9ec29e8dee6d384cd6a318f3d14cbeeea13ae8abd96338324bb3ae9b90bd9a0d51ccb63a14f9daf2c14771cc8788e2d56c872f91c7f1845ff2142f91dd1493ae4384fb061ffc249ef22112f51219f125f2348efb11897a891ca09883758870e3e283e90b53ec257d459820cd6dc9be6f100e5defbb8dc66405b13efa7c8d70f07adfaf68d8cb79eba7cf97ece19e741b6d3c912f5041fc2a9aae21d66ff90520280041f2052b58e002820e20240021f285aa01210710168030f9c21534ba80b003500440219f5241930b501c405400a2e48b56d0ec02a20e20721bad3c912ff2231606700d4d6cff0639740da1e075f73aaca12f0b5041cb54d21d54994ba9a292541a7ba838f294d6cb94d86f29437f508efe2475b758fdcdd2d5519fac0cb2a4fa52fc058d7c019c24770f3ae546e96f2c94915b1c913c7fedb1214ed7b8739324d34efd0d918405492b012dcc76b07959d7b8909edd247b2bbb8f3bfe90f087bcfeb0f093c86d357ed9b51481209517d520a2c24fe2b6d7f849d7a6a4a5e9620962a56b46cb1588d27812adc851731da7af2d9d949c204d4ed1bfd5795cc124be43a1aa826fd81ab6e20bc2f29d66e574342e1c92fec52204483b92896cd8764092c6e67c3c3ee7ea91da473a70c2fd22ba4a94f8624efd8c8ea3b7d0d97257055d220d4b5a960a9bb4ef6ab95bd821b697093bcc986e61cb15f4b4f40a5b61ff3dc28eb2bd5cd811b29b2f55706f052bbc4dc2a86a12536e933865a1f2ff01504b0708bb1cc2699006000012110000504b0304140008080800f984354d0000000000000000000000002c000000696f2f6e756c732f766f74652f636f6e74726163742f6d6f64656c2f566f7465436f6e6669672e636c6173738d56df6f1b45109eb5efce8e393b89d324344e9b382dade3a4359442a129a524b46952a73f7049dbd0d25eec6b728ded0bf6b902557d40bcf00794870a211012f0522490488a40423c21c13bff0da27cb3773edb57a742d1edcecceeec7cf3cdec3a7ffdfbcb6f447484d663344cf33dd443677958e0619187733ce463b444e779cb85085d8cd0a518c5683e4aeff05ce0e17294de8dd2328b57a27495e76b515a89d27b2c5e8fd00d413d75c7a83997ad8a29482c0a8a98d592abf559f5a546d9b136cb66c12c9b45071b5604252ac687ae3e6737aa6c5c1014bf6b3be69c515db24bd6ed8f046927acaae59c14a46616172797052973760967f6e6adaa79be5159356b978dd5322cc9bc5d34cacb46cd62dd332aceba551794c95b76aeda28d7737c7cae68579d9a517472159c55ce2d7348bb7adb5a9b016cc45959e0485129adb0a816d7cde286a0706612c0e305c7286e2c199b5e107dcd740aade4b109e9ebf50ea39291e863d87abac94bacdea6f44309b2a46464f4442560efc7294b01f21075c13b25b0a264643abd567db993db3e6c0e9834f383865106638399fc1de3ae912b1bd5b5dc85d53b386e8673173633fdd49264da70d03ee06ddda8afbb55d26a661dd86173ec8253b3aa6b82766526dbfc5d2bfc6305bb512b9a672c66b4b75592c3bc57a7717a5fa7e769b74e233ca46854a73db457a7311a8dd04d9d6ed1219d0c5a1574e0ffd55ad07010c66cc32a97cc1a18bf37e177f3c4f1719d8a54e201d0e2d3e3135e676345d010f4607f7b2eb7912c563bdbdc5b43f801ac7574bb3c4fdcd7698ede46758224cb1aa39023cdfcfcd4eaa58ddc6cd9e60eed71000cd82b9b201d6d325736ea28e74007e9d208ce35637313b9083a9479ba244f57c9a3078ea3e8e6672eaf3c7b7961e7654ae3451ac6aba552880b0e29c4359733ca2e67545ece6352ef2781fe48639c80768dc2f025eacd3e26911ddba25036bd45e11fa5c33e8c495230c6312610aa17631fed87651c8e70a317e800919438bc90120308cb3007bd301f438f604e7586c90eaa5ba46407b52d525b214748f3020f20e42e841cc43804fbb00c7d54224ef9a1537ee894179a25cededdc7f96b124ec683f319f64431a77786931d8c6c91d602b557e2df0d5023009502a85180da036d2f0e1e93c04e4ae0691f58da0796f681a57d60690f184b5c9a08764e52d683780f91d8f370f627521e521c93aa7c4d6af811a410bef083cf3d2dfc33451e7cc18af2081ee136bcfb508dfd28f941c4ca20ca14ce9fc65f4ee2d5dd081ede29d946e1fe389ba7e99007240702789bca71bff7f9d0a4f1e5b67354ef1c8113739ef349a0e1ac3549728bcd1866a263f0794d9e302475cd674ef39813f4625720e1209099ae405eea0e241c04f2267c4eed0064c40392f24b9393ba0b4432de0e64ae2b902360aa0924e486c8a6d06b4120f3f039bb0390a3e8110efc0abdda05881a0472ae2b906320bc0b103508e4027c2eee0084db9603bfde95112d08a4d015c8f1ee8c68412057e073750720631e233374c23beb6fdc1a2e73293bf52deecba3a93f2891dda6e814be6ff8864cfd4e3d4bdcc9d3f81e3c6cdea069be4f4d4d81a67ce9292a14b5a96850b4af28a27c474ab875d726e463721d37ee06faf6260832709b56415091ce50090c986d6f66c9cfa0446f207b81fdf368c103c88fef603ff54412e21f4a46e8545cd713a738c5b7fca2ff89c499884bde3b10ea1bbfffc9a727fac652eb9c097fd272ab69c2ebe102f62daa2f69adb5542b1ff701be83376e036f531995aa0069154d61b755e2929747023d3f2b5b82d1674979824b1b0278e0164fb89c520e09fc1bdba1c203bfdd5e5a77e52f0651fe578a5d7b4ccf25f56df9e885b62991ec75e530cb7daeac6c537f32e9caea360d2477b9b2c6f641c8db34f443a0139db64eccfb9d785aee3af31f504b07083314dded7a050000ff0b0000504b03040a0000080000fa84354d0000000000000000000000001c000000696f2f6e756c732f766f74652f636f6e74726163742f6576656e742f504b0304140008080800f984354d0000000000000000000000002b000000696f2f6e756c732f766f74652f636f6e74726163742f6576656e742f566f74654576656e742e636c6173738d55df6fdb5414fe6e9cc449ea266dda655bbb6e25fbd1d449677e8dc19a75dd4a0761e906145582279cc44abd7a4e499c4a08f137f0ce3bf4a50f20b14e3009f1d449fb9b10f05ddbc95aa70814e5dee3ef9e7bce77be739cbcfcebb7df01bc894f32c8e3661a09bc2b97f7e4e32d15cb1954717b0c2bb82dad3bd2baa36235830c6ea67057eef7e4b296c2fb29ac4bf3be8a0f547c2890dceb7856ad2530517f6cee998663ba6da3de71dbcb02aaed594f6aaddef0b0efd98e51b77b1e0fd39b76db35bd7ed71228468eabd1582bbc90acdaaeedad085c2e458fa3d117b704e26b9d1643e7eab66b3dec3f6958ddcfcc8643245fef344d67cbecdaf23904e3deb64d9e0b75bb63b87da767c8b28c66c7f5ba66d333ac3dcbf58c2d62ebd2229bc2c9285fef0e22ddf82f76a71427f9a6db96b7156a992f2d8eaa99a1436d20e8d0e3b8a45746c0d385b4beea9b0e839c394ef551e3b1d5a4745f08888e5469e4881cf7060a088c6f7a667367c3dcf5ebe61409a4b6cdde7620bb525aac3153d7eaf51d3a0b3ea4bccea6d7b5ddb6c0f489fa025456b8d9e9779bd67d5bea981daa7d5dba6a98414dc334ce6828e0ac868ff040e0eaff6a978aba861216556c6830f0bac0d968f67b7ddb69595d81b16f8ac144176fcd6b78884772f998e556e68be138f384157dabe12dbccdd18eea243033603524d46bed18a16e5486dd4eb1996b8ed96317a64e88e183d4227bb2710360d8598a6beeee5a2ea765a9342ae6a8be6185cb11ff41dbffd51faff1a7220ff2448c1f3680bf2531d90362e77cfb3c92b467304bfb02911dee0af79cfe0c422f3f454caf3c85f2332105735cf38873d5b88e238d2c3f395c24321f5cc3255af02d994ef8964c182319796f809c2712502812bb8c2b5c250183bbf449e8bf20f693ef20d3267d30efa70a8224c2549c245c0bd91fbfac442f174eb91c305820c6190b19bc082568e8e51f91881f948f90d50f112ff3fb0312ca41f90f243624bd234ccbadc2ef2192df23b78f4cc5c7e925291c614a6ec4945fa1c6b08f9cb45f408def23ae1c0c552d9011d804950cce91e71cd9ad50b157ca3686ca36a0a34cae73584785580c4b44279156b3e24f5c50713d290a9955a9045f98b0a8effc09a04201ef71dde79c922caa139766b703b639dd679a1690075f56675f31ccfa2cae71941698afe4332b04214366534cf406ffaa84cfe722e293abe26fd979322219da08ed9890e4f80a86e43e651cd995d273643e7f86b1bc76386098cde7025b91f604ed434c463bbb74acb3a5e158dcf0bddef907504b070805776153a803000043070000504b0304140008080800f984354d0000000000000000000000002f000000696f2f6e756c732f766f74652f636f6e74726163742f6576656e742f566f7465496e69744576656e742e636c6173738d545d4f1b47143de3afb597c50607080d2121a421f6da6113fa91165342e37c94d469a55221a54f2c660b9b6c76a9bde6a5ea4fe81fe87bcb0b0fadd438522b557d0991fa9baa366766d72698444296efdc397367eeb9e75efb9ffffef80bc0021a3a8a58c8218df7a4795f6e3fd0f0a18e9bf848c7c7d2dcc4a2f41635d474e858c86249ae9f48b39cc5ad2c56a4fba986db1aea0299bd207456b704461a8fed3ddbf26c7fdb6a04fe764d409767f5c0ffd6dd162835dcc0f23b5edb92a8d50cfcb0653743eb69b0e578d67a3f92f7324baeef86cb02b5d2e0a3a77da4bc2e90aa131528345cdff9a2f374d3697d6d6f7a448a8da0697beb76cb95fb184c853b6e5ba0f2960cce9ee3872ac32ab9dd953b32cd6d3be17a2c40b1543e29c1701cd053c164d0e97570beebd81e498dbfaec3979b8f9d66582b7f23200259cc8923558c1d32fb5a68379f3cb477558decb44076c76eef44c2244be555266939ed8ec760c14d360cd6c296eb93e9d8b1722254f6742de8b49ace3d5709794c8f79196e6012770c8c61dcc004ce1ab88b7b02e5538baae1be81cb7857c367064c5404ce0eb2b8dd71bd2da72530f4fd6c347db38b330656f1409acf398ad599d9a3d1e3218bfbc1c03c2c9e0daa2570ae47aecfabbdf5c4526c94481ca52cdb58f7ec367b71e6982e0aa42cf9e39d17983b5d97a9bfbdbbebf89c9f6ba5937a9f6c415c7c6d20be37146f8dc725fee28b207f24f8617ff89790902de29a213e897768cf71f78878926bc17c0e61569e2161569f21f99bba30455b448a7688d6400ec3c8f3739ec84c740dd3b800284fa611ca938912f42f322a4a6371956769f377247eed3f9e51e0a87ad08802e207056b987dc3e5e4e0e5f1375ee650c5975fc4056e98955f904e1d540e9137bb4855f8fd19e9e441e56fa41f4a5a8718934b95df2e323fa1b00fbdaa7046c9d48718950bb16417da3e0ad27b092db58f54f28039928ad7047980b935ee26c9689a3a2c538923d536faaa6de00ae6c8741a755c259640490992d3f2e25f9cd750ce88317d45d6cfdf475cd28faaabc0b588f5b0a91867258ba5918b533b11570993674ec21b4b5347fcf28ac3150ec71c9fbcaa784d440fc6bcce304d953ba1d85c406a7445fc4fa513e4432af411fb0921a9cdab16496a5fa9be03a53fa13f7a8ea1a2d1edf1cb170bdd1ea97c71847e17a383ddacbed6cd52bf9bd755d48d57504b070865f9c6c86d030000dc060000504b0304140008080800f984354d0000000000000000000000002e000000696f2f6e756c732f766f74652f636f6e74726163742f6576656e742f4164644974656d4576656e742e636c617373a556df531b5514fe6e76970d614920fc865268501b36d0adda5f02d214a44a0dad4a8d6db5d225d981a521c164c38ce338e39bef3efa5ccb0b0f3a23746c671c9f70c6ffc3ffc251cfb9bb8949a04e1d27c9bde79e73eebddf77beb30bbffdf9f46700af613d82412cb443c7220f6ff1b0c4c3750ebcade39d08967123827771a30319b696b1c2cb155eaee8b81941140b61dce2f93d1ede0fe3833056c3b81dc687ecc8eaf848c71d81b6dd92e72ce705ba325bf6ae6d15ece286952915376629e67ace36c73ad8582c153da7e809c41b3257bdb22b73354ea9d48fa97a6ec1cab8158f42edabee46d1f6aa6547e04a4b782ee396ac62b550b1188795a33bca76ceb3b64b79a76065191b9d3b3bcf68e6dca2ebcd0b2c255ba1fecb3a00d88a6a322ba02ed22502b18c5b746e56b7d79df26d7bbde030c152ce2e64edb2cbebc0a97a9b2ef1339f03d8d9a5da58d7f279c6bbc40b82dcdf7cd0e73bb5c3b6ff3f8517ae1c536ddf70bc6c20743c39795c6a4e580ed48e06765df0dea61d75c9c3415ea5e1cc46dd678e39ff8bdace6755bb4047f73596ead6fa969323f5ee0988527327062129944da03b573d3bf770c5de9125a7a786006fda954d5f742539b94c97949d4ab540c9821661afe4531388ac96aae59c73dd65adba1b453dc7171a98c05d03c31831708a87519c3630867103f7f0b140f2455b44c727062ee0a28efb3a3e35308b398181d6522f54dd42de29d363f845c27f5a133374d51a1ef0600b1853e309ff51a50055255806fa912f413989c05b9139e24b0357719e9ed7d60a0a0cd7e0d79157f20f2d0958162eeb4bbf58b02ba44f4f5373482789106deeb0a67b6a558e363707c961efec3845eac0e9e4f17e3bde82415d665bf26b3df2dc7c9ca197e9200486a022c432d27b37c44aca99c49433e9493967a49d4098ec09bc44f6cbe4f986668de661f30984993a40c89c3a80624e1f403587d403683f5054c12b9c83361a63745717dad14d2fdf387ae833845e9ca5c805ff24243109488b11096931a690b41895222dc6a5c224db08b2c6089f1a204d916f0ad334324e8b66ced1cc1f11fa5e2630a436e91c90d7fb8768c1f502e74edcacb46e1e3971b385f3276c565b378f9db8f955fae3173ab6596bdd3c71c2669ffbebe4a3a7c9472006c8c3157b64a61e4353f75347889a87684bd1ef3b68ca7eea17e82b5c9823f4f23445bf4384bf456c0f9129e9a72c26ef2728e4531a13945a82ea27a8e4530fd15e4f506b09da117a78229ff6132221ec21c6f6afd0d53da8ca7ebd5b4e536f8258e9c46b90d41c2539e64994352aca57545de63eeef3aa77cc235cc265623d8aaf71857c21bc21e58de951f1074ee998691303f2db1f497365e93d13c8f43b257383adf975e834650d3a18d35cd7d8c8a6cf9edd4ae07e50f3abbe9f081b4d7ee21a3325cf4e013f32f20fbf7ed9ed17a9ff2f11bbcb5824c4f709ef59199350025e3df4cfcc9bc45d4836730877a7c55fe84388f81009b211d82121b2cdcb3bcd4b3ae36abd33b7e816ee9df43344ef3e412cde75886e9f773cdee3db0adbbdbe4d04bbe37dbeadb1bf9fec430cb4f6e57c435fa6eb4d9d9659d7fe06504b0708c0b5494782040000de090000504b0304140008080800f984354d00000000000000000000000031000000696f2f6e756c732f766f74652f636f6e74726163742f6576656e742f566f74654372656174654576656e742e636c617373ad55df531b5514fe6eb2c926211008bf7fd3502d24a1b12d6a05a4585a2835d01f406cab952ec90a4b4382c98619c771c637df7df4b995171eec8cd051671c9f70c6ffc3ffc251cfb9bb09c926ccc88c03dc7beeb9e79efb7de77c7bf9e3ef9f7f0570153b01f4e0ae1f2a3ee421c9c3320f2bbc714fc5fd001ee06100ab78d88435b61e609d97ebbc5c57910aa005777df888e7473c3cf6e1890f1ffbf0890f4fd9f1a98a0d15cf04bcfb79535fca08b42677b47d2d91d5725b89643eb7352de0310d33ab0b84abb656cd822137958c5e4c538c61eabbc5caf19269641349a36852847fd5d8ca6966a94029ae3bb66792463e912b658b09be3f91cee7cc82963613bbf98c9e4da41813e59d9ea534de19236798b3028b634e88f5b8cef29ce21a4f11f679ba4620943472fa4a6977532fac699b16d37c5acba6b482c16bdba998db06319c3803b2beafe74c0979bea06ba67e9bd784bbab36d7177be57cf9ff83c77f2e20f3f56fe966caee73786cbcbed3fee269407b1d3c4ee1a3146b961c3a6a325404e12b56223ac7ea0338894a496e49d9a8c5b2c589972c0d55a055ab68aace790eed30263b799956ad12a69dde73d5d5ab7f5ed2b24507e17b9b3b7a9ad23f1110f9daafc7de929ad24c81e655534b3f5fd6f6a434e8d326c8db5a71dbd2a77b6c7c892e29e8c5529682052d7c66deaaa74060355f2aa4f50543f6c4a1bfcb7c6710a3a0a10ffd410c603088211e863112c426a8f4b173085a45268819bcaf82607e16c43c6e09743bbb7cb36464337a41a0e9cb88f5b044a6e8b62d6cf3600804e32311f9aa903f42cb08adf921a95aca0785d644f8ab201671851e17670505facad82bb08b99e709095516ce96ec7c562bcae6574b563aa9092db53aafb9a75ce5965a79503bb4bd3d3d47dfc9442391d7b9ec8a4c3be2cb1a39331e17e8c5ef81402f14b8b887f4cfc1c56d94f3903d533329e682b42308923d8a8b64bf419e6f69f6d0dc177d0d118d1dc1158d1fc11d9d388212ed558ee07945bb6ebcc931f0d218a2bb5ae1471bfd8708a39d7e7ad1814bb4336965c218c601693122212dc6e49216a3724b8b712988921db4a386099f62238d912f8e091a196782668ef1447f84eb0719c090bcd2d92dafb79278eceb052ecb437c7896a2f972af24f8aa723a20bdfd74664066e8b2a22a04bc360181b770a50110b713c87043205771ad1110b713c8289db9780610ae1f5f3cd91088e20472a92190b71b03519c406274267e0690211bc83b78d7965035108f1348a20110abbfd7c9f71ea6ec24f7ab015569ce02748dce4e9e01c852f7a98abc524565bd4f93452f92455b749387b5f7221afb1e1ee530768296e831bc31fa7b098ffb30f61bd46596d8093a788ad3df317cdf217480405cfa298a5b6f05b8c9e73e86bf12e02e07285680423ea53a402907784ed0ce13f93c3f21e0c201426cff0e553980e23eacd46088be7250bd54aa570f311a243eb3f4b3811bf81a73b22e2316af4a5d5ed0fe0d623d886f28629c787f203f9490da22fec2808a9b5ed12d7fbb0273dc3f7aab6d6dfc69b762c3aa437354d6a08931cdb40ef76f5becd94dcc83ec7e56f62b965f71fa896b282a79360b583bfda7fcbae4bb719b5e920592de2241b983a758aaeaf786cdab1d498a5b20a0cc6606beb639f10f3ae1223e44826cd8b64b8854edf251ed92722c56bea51dba85b533f70b5a1ebf4628dc7a8c368b7738dc6ed944b62ddc61d90adb9d96ede1982eb28fd1ed54ff4a95fae72a9fe11d19b5f42f504b070809f8fe4ffd040000cd0b0000504b03040a0000080000fa84354d0000000000000000000000001b000000696f2f6e756c732f766f74652f636f6e74726163742f66756e632f504b0304140008080800f984354d00000000000000000000000029000000696f2f6e756c732f766f74652f636f6e74726163742f66756e632f42617365566f74652e636c617373ad580b781cd575feafb4bb33bb1a215bb26caf1fd21a1bb37a2c32b611b68465d9c680841ff8fd2286953492164bbbf2eeca609ad64d6ad214f2685cd2143b84779ca60e016ad67294389424a62121d0049216d2269486266dd3bc9a845003f9cf9dd9d1d396f8be7cb667e7de7beeb9e7f19fff9ef1b36f7fe10c80c5aa3b84760c18381d4201068268c317e43118c217f125799c31f1e5109ec23fc8f4d326be62e2ab26be16c2593c63e01f4328c18089afcbefb3f2f8863cbe5984563c17c2b7f0bc81174298810179fc933cbe2d8fefc8e345032f8530578eff6e08952253293295f85e11fe19ff22322f1b78457ebf2f07feabbcfd9be8fe81811f86508d57c5b47f37f19a89ff08d1c01f85f03afed3c48f45e82726fe4b76fcb789ff91df9f8ad6e74cfcaf08fd4c1e3f37f10b59f9a5815f85b002ff273a7e6de237267e2bf36f98f89df8f3a689ff97cde7c4b8b7c4afb74dbc6328508952a62a30546108eb95cf547e53054c6570b73283f8a20a9a2a44db55514859aad85417c94a890ca6c8e6a92155aaca78ac9a26c372534d37d50c2ef30435d3506143cd32d46c8592de4472b3dd9eea4a266e8f27db6d85e9eb6e891f88d7f5c6b3dd75ab135d2dc9acdd65a71b15fc0752593bc32d8e407f36d153b73edec795e0964457329eed4f737bcbc8d5ab9c614f3cd955b72e95ec6a5c9748d525fb7b3275a2adae3d95cca6e3edd9bade5487dd53b79d736b93d944f6606313f516898c5897eee0b9e989348f5ace1fe49d91e9d857b7aaa3236d6732c385d72532d931ca9af41f85c055896422dba4108e8e1f97aaed0abe35b45e029348da1bfa7bdbecf4d6785b0f674ad7a5dae33ddbe3e9848cdd495fb63b41772e394f243afb93ed75abe3195b82410b8ab764e3edfbe891deae53378776b5a7ed7896dae2d161a66fc9a6135e2486cfec193b5535d94cd0e244d6ee555838e18e16ca49d044bea54341b5103654e2c4628c09d4dc6167da29231b1893b271ec14e0c57bfaa92120c78ada29a3b3c58a39e019ac109dbc6ba69c2c08f0b47a9868945a1891bf837df91c2e1b839f4985a6a9d150730dbccf5015243043554a6c892f85da68eb842ad6a4929d09266e3703db2a10d0e3c9b8ebeee46922c1add1d63dada2c73ce09a4695fc6776c7336b6fd3d150724a42c17072c9e414ece1a12167e844cc48e76bf3e289eb4961d31fbc420d15215bd2aab4dd61f7f66513a9247d8c6ad78cb6788fc368467b3cb95dfb1ddcdf6fa70f3aef35149b3c4e4abc9d9bed4c7f0f9d2f95fda39970eb98c93f8097ac0b7dbabbe7ba78c6f1e0e261981957b90e43dc1928545c5858c1eaefeb20a7906fb2fd1981e464c3d32224589cd1fb649a65cb58a7923d0737de9ab4d34e4a2812da92ea4fb7dbd724a4848af31c7799b86ca157cd53983a140ebad9cdf859e842b7857de826ef59ea6235df520bd42564a35137572469db1d912e4d8be948b63b9e8cdc6ea753865a68a94b55d4828d4e4652334d8488882453d98880868451a6396af46ca990d3c84943555978bfaa5698319aa856f7277a3ac4d9999aaec631c75235aa561e314b5da6ea2cb5485d4e2610a716d39fc905db524bd4524b5da1ea2d75a53c9681982f1b8adbaa743a7ed0a9cf059321254b2d57f5a4224b35a8464b5da556f06ebac0d524fb1c8858aa49adb454b35a65a9d56a8da5ae566b5957e3efb50fd8c9ac43463a266b652c59bfc652d7aaeb883e87dd23763a9d6214cb1c76f3c24f953d164ee249d6bcc57ba555b2d6cd9c3962898cb3d152d7ab75049728d3fb3a13c90e4bad5797135c7ad281695e7a83dac85cea0541c6c86c5bea06d56aa94db29917ff6d918cdd63b76779667f32eb890ee5d84e3a4a3289db6d4b6d565b14aa268a460be9df8bc5560bc7f188c2ecbc6ed9551be9ebb1592b91f66ebb7ddf650ae5fa9044c76863b729d658454ad3a0acc9529b3d16833b14e64a714632fd7d7da97496f626bb087fc73b6e9620eeb4d42eb5db527b1499214cfb6c72acf818b9354d628ab83630d423e8ce52374a6a8ae33d3cb5e3a076a0e3bc801a0a831782f7f02276d291f1f2a730a7bd3f9da64464f852ea00bd3a6813e8657a9a97979e6fef49656c267dafaab6d44d52f515e9e14421726d369325b786dd4b517c5ead32d4cd163e2da5bd7cab832b6d63c4bd46e4d01ef2a4134689c3089df15ec184a5e2520c6d6008db5587a108834e45fd8fe3094b7529e95d5c3ed6887578a6c4bd46b55f84396b21efa1439ff327d12952f310236d6cbb8599646730411d4b139bee8ccb25396dbcee76845287e6142e1a7945e527bc3b6c1254b6c6ed5c0aa3722d189a3037764a939dbf3ec77e7e04db53bd7df1b4bd357581669cfde6ac716fb96db42ea33b96fdfd09f94e991edd3d4e4f4c6b668ebb7f7d86e6ce8c9ed7ba40bcafcf16a4c6c6e9c7abc64cb99745e32879276f179437b3a97c22a645c70a4a8f27f4a363cb689479111dde2dfb3376561ae9b2e8e8358980c9d5ad4ed35e3e9e3792328a5cad5bf712be8dfc800c718609ceb25c6905e13e4a4ddec9ddce492d6eeb1f1ddd7ecb39410ae4fb115f54f719810ce32cd08c44ab26ea6844bfdb82cc8b4ed02b5175615f3f4d5e368eb117ce913ba570ed98784ee6e36c3cc77d76af7c16549ec76a4d975ad0af6f059d6e06b4b04be870e10498f2ec0d760d85d7ecf272511aad1a6d1399209159cfc637c1cb688bbe2878a172cbfaf86dce708d10a093b17c79d74cfacb443c691c1bbc77b15b5cc99f5b1d9db8b3f73e89e6105617a8b7f291cb5ee8fc5d4e099546c7a9af0059262e7c5392d00df19a78727daa23d149b29f7f1e888c4c7f49d7e8aaf2ed5ebb99adca9c0b81d849a20b7893cbc94ca7bc5a4e9ad3acea5e87195acfc793ab7b5282a6609692ec947afb58cfdcbd36d9217b310f6d68e70757070a51201d35009f74e89c4bf0bd00b770bc6fd8b807457cef4592cf1467eed63b812baa4f41550fa260d729143e09dfd0abbf263880400ec6a760f88ec357586a0e20585df324428f736321faf89c85009f7379d82c9460369f15a84325962282fd5c99ee1c823432807e136315b27c9f8a827750850203fd060e00cdf209792b6e732c2c5846d5214085626761c5ce785614d18a9ab3f991c551ad372ae668001735f8c2beea9308d1f863eed2204ae8d694d2a939943a4b65394c13874ec2771ae5cceccd770c607a837f10332839b321100e84fd3984c3819a1c668503b539cce6942f873983984b918a06a3b0de2c3763671ec4e24154722ad2100c07cbcd3b4271aaca615e38182b3717e77071d808074f637e01761c36d5f1779ea322238705e140610e0bc381015c9a43544c09fbc381d3a82ac48e4154ef0afb6b6ac3c629d40ca0361c784c6759c2de86997c2e40192e21161632e45186bc0a7b51c3d8c518c33a1cc222dc81cb711716e308578fa11e8fe04a3c8a6518c4727c150df816e75ec40abc8226bc8a95f80956e1b758a30cacd5e9eb64061e652a0fe27604f1294aff11de0b53b2924f29dffe187fc29cc9db21be15e8b73fc5fb0891a5f82ede4f2d3e5a750c7f86c3f0d3b623b4eb03ccee31cefe393e08037f41451675383377724600528f5071b3b2f4df73081324cdea1dec445023e62efdf7439c35f061031f31f0511af12621a6f097f8988322f55e9e2d101dac0cdeed4128c6bcc73cd05c2628a83c85ba580e8b4a2f773151c90c9ec6e2023c851982274f7c090504054b8fe667ae7067ea1bfc61ff594c0dfb4fe34a05efb865725c0ecbefc16cfe34b8cb0fe5971bf576ae5c15f651cbc241acd825a7c74ea18979f79df0d2dec41a035a3105d7b3ced6633e36a2169b38bf85a36db8093b18ec9d4cc36e06780f2dd88b8739fb59c4719a8421295dca14301c5e450e322d47247d7cfb2b4d0b4d4ca824c8475ddb98c80f72473e41cecc9d9c910455a238e46627c60c34ab29f25ac14c34abb9cdcac70d1fc75fbbf5fc732a2c664612d59539ac2c6d767230bcb2579d27f439ac96c8ea08d6c6cec85bc343a3ea7a0deb5a2fe470b553d87ac35194c5cef8eecf9fb0d6cb54606c0db7b156eb84028285f5a1c2faa2f222294566ea41548703e545a7718d9854d96085ad1cae0d0773b8ee1e047df5a1e3081e2e6259bf501e3a8aa2c2d2161e53786254cddf4bd7fcc35c6b6d30c3e63342269a701bccfc7ad87408403b9ec3f547c93ea4de4bf5be67f2aeac1357643a6ce4f962bd868df0c58691b8f9004902bc0c42bc0ce6f25aa8e37550cfaba08989e9222a5244c321f453ee003eca02bd8705ff204bfe1116fb49a2e16996f5f394f83673ff12e75e6121ff9018799d3b7e4c7cfc927bdec05d2cfb0fa9127c58cdc44754141f534b7044b5e2e36a073ea1baa855f0779820789ac4f509eab7f0192cc1df506311cfbb81e4729834f308b17c0f8ed2da23c4db319770121ee124f288e5db27712f115ba71a5cc4d6f34c41b19fa3d7347603f4e86556026986f6dd84fb180753e339c29d8ed49d9ed4fd9ed40394128c2f47e98c66f51696688a79b05985a712d96fa3942817ca791be5e41fa56adf84df7a0325e75067e0a1d90efa1fa6330e0fada07152780f8fe6a1d180df30444c1b1dde586adc87725d341ee3dc905ff190bdc999d9fc04b60cbfb2b7ea0b715b0edba5904460d89d58bac3d9b4d399df15d39be768e038d7fb62a6080ccb14a6671ebda9c3a779831c470b29650ffe8e29f91cd3f828f9f60926ea24a5723ac9d5f496be7a24f3b09bb279fc95441572573575c9cd304424e73057b3c84c06afa85995cbcc1ccdfadc7adc0b65d40de5a18943f90cfc852762c21ff45e0fa4a8760f60cfddf72214d324ee482c254904ab63fe53b85146fefbf2c2ef11e14f92469c445086f57563e18961516a45a9a6cf29f8123dfc32efe4a77029bec27bf86b8ce05952eed7712d9e65d4be41b2fe2649fa7942ec0592f277d891bdc8c27b89d0ff1e0bede561d13be445ef9017bdbd6ef462d8ac61ee446f06423a7ab319bd73b088d252551c0c4bd43e83bf751bbe336ed456bc7b00d637f862128a05b1a10ec469fc7e409f5fa55daf11193fe2cdfb3a1a4908433eacf07c58e1f930c7f5e10a169814a8cfbb5c9c993b5daf8ae11b86092e7f96802bd0be7cdff565d3bbf4650c036b5414b90cfc58d837e45f057b10e0a7f4ef67b4f917f4ef57ec8d7e8d6bf01b6cc0ef86f9b8c9f37193e7e322d7c755dc2f8433e4a333f380eb6319fcc37c3c87f9a40eb7613f41e1cfb1b41ccc5fe4b631fbcfdfc6ec9da871193702ba6371056ed2b7664cafe5274f8c8ac85bb4ff6d54907d17a902ac24cb6d5201dcaccc61cdc57e2f22fbbde6623f3e8fc71891458c8043d59b48c0129ba1e6c29979c06d2e666b54ebe6a2d20d0f7f1f6a5665444381fc579e8beceb382ae46fa0660ebba7c775f4c4e0906e452df855b1362ee24879c605b4214abffd3d6f9902fe3989275dadf7d34449efca775f2f3b753b7f73293f0282439f4ad325366a0aa6a852cc53d378694d47939a31ccb4959e692b3d2455b8481ab72272dad753bf07504b070807211fb79f0f0000ef210000504b0304140008080800f984354d0000000000000000000000002b000000696f2f6e756c732f766f74652f636f6e74726163742f66756e632f566f74655374617475732e636c6173735d905d4a033114854f3a6d476bffd42717e0ab017730142b01e90849c7c71243949621814ed2c5f9d005b828f1662c74f0ed7e5f4eb827f9fef93a0278c46d8eeb1c370c73a90ab5969bb742a88d5809c5c004c364e15d13b40b95aea3cde80efe65ab523d25cf18a6279f94583d27db63189fec6bb1966d323bbbc54bf9e7fa0c23e9e3ded8e5b6b60cb3ca072b830eb179d8e98366b8df7aee62ddf0039d70e35dd86b13f84774869fc3542ec579addd272fdf77d68421bd043dda31c01d32f48906a91986c479872f882f3b3c22beeaf09878d2e169fb19b3769eff02504b07081b9fa402de00000052010000504b0304140008080800f984354d0000000000000000000000002e000000696f2f6e756c732f766f74652f636f6e74726163742f66756e632f566f7465496e746572666163652e636c6173738d50cd4e023118fc8a2bcbe20f08fe9d3dadd1d8c42bc4c4188d9235266238c8a9760b292e2d76bb24bc9a071fc087327e5b086220d1ddc3b4d3997ed3f9fc7aff00807338f461df870302456e04b382000ba3011b339a30d5a76d6ba4ea379699ee32751c494d5596a474acada05c2b6b18b774a86391d00e72d7ca4a3b6910f0a49296c069d8fad373a5554fe2e5cfe8ca3598346c755bf9dee74c751ce5858e281b118be1c84aad08046f993093e9f9099eff3f5d65ee7c14699660d05aee770fceac4ce83d1ba12c68cbbe62363338e06949d09ccf9b8f4ae3577a19c746a46963411cc9d43617ea8c349679813f81ba4b32f3dcb274fa9aa385da565eeeba68ebcc7071231374d472e39db2c2f4181767f92c02e1ea427a99e2f4979e40f527ddc3cb40705b2440a000f957f208ac810780b80e45873e941c065076b831c34dd872b80d15f457d15d801ddcd7a08e6b02bb8ed9fb06504b07082c00d50e580100009e020000504b03040a0000080000fa84354d000000000000000000000000090000004d4554412d494e462f504b01021400140008080800fa84354d764604263e0000003d0000001400040000000000000000000000000000004d4554412d494e462f4d414e49464553542e4d46feca0000504b01020a000a0000080000fa84354d000000000000000000000000030000000000000000000000000084000000696f2f504b01020a000a0000080000fa84354d0000000000000000000000000800000000000000000000000000a5000000696f2f6e756c732f504b01020a000a0000080000fa84354d0000000000000000000000000d00000000000000000000000000cb000000696f2f6e756c732f766f74652f504b01020a000a0000080000fa84354d0000000000000000000000001600000000000000000000000000f6000000696f2f6e756c732f766f74652f636f6e74726163742f504b01021400140008080800f984354dea1b2f50bf0400002f0b000028000000000000000000000000002a010000696f2f6e756c732f766f74652f636f6e74726163742f566f7465436f6e74726163742e636c617373504b01020a000a0000080000fa84354d0000000000000000000000001c000000000000000000000000003f060000696f2f6e756c732f766f74652f636f6e74726163742f6d6f64656c2f504b01021400140008080800f984354d5259657c57030000b60600002a0000000000000000000000000079060000696f2f6e756c732f766f74652f636f6e74726163742f6d6f64656c2f566f74654974656d2e636c617373504b01021400140008080800f984354dbb1cc26990060000121100002c00000000000000000000000000280a0000696f2f6e756c732f766f74652f636f6e74726163742f6d6f64656c2f566f7465456e746974792e636c617373504b01021400140008080800f984354d3314dded7a050000ff0b00002c0000000000000000000000000012110000696f2f6e756c732f766f74652f636f6e74726163742f6d6f64656c2f566f7465436f6e6669672e636c617373504b01020a000a0000080000fa84354d0000000000000000000000001c00000000000000000000000000e6160000696f2f6e756c732f766f74652f636f6e74726163742f6576656e742f504b01021400140008080800f984354d05776153a8030000430700002b0000000000000000000000000020170000696f2f6e756c732f766f74652f636f6e74726163742f6576656e742f566f74654576656e742e636c617373504b01021400140008080800f984354d65f9c6c86d030000dc0600002f00000000000000000000000000211b0000696f2f6e756c732f766f74652f636f6e74726163742f6576656e742f566f7465496e69744576656e742e636c617373504b01021400140008080800f984354dc0b5494782040000de0900002e00000000000000000000000000eb1e0000696f2f6e756c732f766f74652f636f6e74726163742f6576656e742f4164644974656d4576656e742e636c617373504b01021400140008080800f984354d09f8fe4ffd040000cd0b00003100000000000000000000000000c9230000696f2f6e756c732f766f74652f636f6e74726163742f6576656e742f566f74654372656174654576656e742e636c617373504b01020a000a0000080000fa84354d0000000000000000000000001b0000000000000000000000000025290000696f2f6e756c732f766f74652f636f6e74726163742f66756e632f504b01021400140008080800f984354d07211fb79f0f0000ef21000029000000000000000000000000005e290000696f2f6e756c732f766f74652f636f6e74726163742f66756e632f42617365566f74652e636c617373504b01021400140008080800f984354d1b9fa402de000000520100002b0000000000000000000000000054390000696f2f6e756c732f766f74652f636f6e74726163742f66756e632f566f74655374617475732e636c617373504b01021400140008080800f984354d2c00d50e580100009e0200002e000000000000000000000000008b3a0000696f2f6e756c732f766f74652f636f6e74726163742f66756e632f566f7465496e746572666163652e636c617373504b01020a000a0000080000fa84354d00000000000000000000000009000000000000000000000000003f3c00004d4554412d494e462f504b050600000000140014001f060000663c00000000a369000000000000190000000000000001010b313030303030303030303002230020516fd346aff0e48d9778cd484dd43271421a08c6bf7e1d7a0b011ac236f9d41101c83c0f000000000042c401000000230020b5a8ca30b15b89eb635fc9ddc8ce47870b5ead7931937f208e95adcade81373001e5051f00000000003cc40100000001170423011eaa95ce055cef73615af36c71bdd3d278c4a28422010a00000000000000000000006a21039a174f19e2539c3c2bd11eb5f6451d0c4d4a6d015a57061694ac4e1a81576e5700463044022068c442ecf02bc9ecb99fadc30144111f7d5a3c0108de8b06a2f112abb7994ea202206d2b014c3bff86eadc322996894f9119dc3fb865cf5902ebe54cf634b874d126";
//        Result result = NulsSDKTool.broadcastTransaction(txHex);
//        logger.info("broadcastTransaction {}", result);
//    }

}
