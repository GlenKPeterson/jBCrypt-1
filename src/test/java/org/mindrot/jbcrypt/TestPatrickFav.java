package org.mindrot.jbcrypt;

import junit.framework.TestCase;

/**
 * Test cases taken from:
 * https://github.com/patrickfav/bcrypt/wiki/Published-Test-Vectors
 */
public class TestPatrickFav extends TestCase {
    public static class TestEntry {
        public final String plainPw; // password encoded as string
//		public final int cost; // the log working factor (4-31)
//		public final String radix64Salt; // the 16 byte salt encoded in the OpenBSD radix64 encoding
        public final String hash; // the hash in the modular crypt format: ${identifier}${cost-factor}${16-bytes-salt-radix64}{23-bytes-hash-radix64}

        @SuppressWarnings("unused")
        public TestEntry(
                String plainPw, // password encoded as string
                int cost, // the log working factor (4-31)
                String radix64Salt, // the 16 byte salt encoded in the OpenBSD radix64 encoding
                String hash // the hash in the modular crypt format: ${identifier}${cost-factor}${16-bytes-salt-radix64}{23-bytes-hash-radix64}
        ) {
            this.plainPw = plainPw;
//				this.cost = cost;
//				this.radix64Salt = radix64Salt;
            this.hash = hash;
        }
        public String saltWithVersion() {
            return hash.substring(0, 29);
        }
    }

    private static void doPatrickFavTest(
            String testDescription,
            TestEntry[] tests
    ) {
        System.out.print(testDescription);
        for (TestEntry te : tests) {
            assertEquals(testDescription,
                         te.hash,
                         BCrypt.hashpw(te.plainPw, te.saltWithVersion()));
            System.out.print(".");
        }
        System.out.println();
    }

    public void testPatrickFav() {
        doPatrickFavTest(
                "Empty Password and same Salt",
                new TestEntry[] {
                        new TestEntry("", 4, "zVHmKQtGGQob.b/Nc7l9NO", "$2a$04$zVHmKQtGGQob.b/Nc7l9NO8UlrYcW05FiuCj/SxsFO/ZtiN9.mNzy"),
                        new TestEntry("", 5, "zVHmKQtGGQob.b/Nc7l9NO", "$2a$05$zVHmKQtGGQob.b/Nc7l9NOWES.1hkVBgy5IWImh9DOjKNU8atY4Iy"),
                        new TestEntry("", 6, "zVHmKQtGGQob.b/Nc7l9NO", "$2a$06$zVHmKQtGGQob.b/Nc7l9NOjOl7l4oz3WSh5fJ6414Uw8IXRAUoiaO"),
                        new TestEntry("", 7, "zVHmKQtGGQob.b/Nc7l9NO", "$2a$07$zVHmKQtGGQob.b/Nc7l9NOBsj1dQpBA1HYNGpIETIByoNX9jc.hOi"),
                        new TestEntry("", 8, "zVHmKQtGGQob.b/Nc7l9NO", "$2a$08$zVHmKQtGGQob.b/Nc7l9NOiLTUh/9MDpX86/DLyEzyiFjqjBFePgO")
                });

        doPatrickFavTest(
                "Random Password and Salt (Short PW)",
                new TestEntry[] {
                        new TestEntry("<.S.2K(Zq'", 4, "VYAclAMpaXY/oqAo9yUpku", "$2a$04$VYAclAMpaXY/oqAo9yUpkuWmoYywaPzyhu56HxXpVltnBIfmO9tgu"),
                        new TestEntry("5.rApO%5jA", 5, "kVNDrnYKvbNr5AIcxNzeIu", "$2a$05$kVNDrnYKvbNr5AIcxNzeIuRcyIF5cZk6UrwHGxENbxP5dVv.WQM/G"),
                        new TestEntry("oW++kSrQW^", 6, "QLKkRMH9Am6irtPeSKN5sO", "$2a$06$QLKkRMH9Am6irtPeSKN5sObJGr3j47cO6Pdf5JZ0AsJXuze0IbsNm"),
                        new TestEntry("ggJ\\KbTnDG", 7, "4H896R09bzjhapgCPS/LYu", "$2a$07$4H896R09bzjhapgCPS/LYuMzAQluVgR5iu/ALF8L8Aln6lzzYXwbq"),
                        new TestEntry("49b0:;VkH/", 8, "hfvO2retKrSrx5f2RXikWe", "$2a$08$hfvO2retKrSrx5f2RXikWeFWdtSesPlbj08t/uXxCeZoHRWDz/xFe"),
                        new TestEntry(">9N^5jc##'", 9, "XZLvl7rMB3EvM0c1.JHivu", "$2a$09$XZLvl7rMB3EvM0c1.JHivuIDPJWeNJPTVrpjZIEVRYYB/mF6cYgJK"),
                        new TestEntry("\\$ch)s4WXp", 10, "aIjpMOLK5qiS9zjhcHR5TO", "$2a$10$aIjpMOLK5qiS9zjhcHR5TOU7v2NFDmcsBmSFDt5EHOgp/jeTF3O/q"),
                        new TestEntry("RYoj\\_>2P7", 12, "esIAHiQAJNNBrsr5V13l7.", "$2a$12$esIAHiQAJNNBrsr5V13l7.RFWWJI2BZFtQlkFyiWXjou05GyuREZa")
                });

        doPatrickFavTest(
                "Random Password and Salt (Long PW)",
                new TestEntry[]{
                        new TestEntry("^Q&\"]A`%/A(BVGt>QaX0M-#<Q148&f", 4, "vrRP5vQxyD4LrqiLd/oWRO", "$2a$04$vrRP5vQxyD4LrqiLd/oWROgrrGINsw3gb4Ga5x2sn01jNmiLVECl6"),
                        new TestEntry("nZa!rRf\\U;OL;R?>1ghq_+\":Y0CRmY", 5, "YuQvhokOGVnevctykUYpKu", "$2a$05$YuQvhokOGVnevctykUYpKutZD2pWeGGYn3auyLOasguMY3/0BbIyq"),
                        new TestEntry("F%uN/j>[GuB7-jB'_Yj!Tnb7Y!u^6)", 6, "5L3vpQ0tG9O7k5gQ8nAHAe", "$2a$06$5L3vpQ0tG9O7k5gQ8nAHAe9xxQiOcOLh8LGcI0PLWhIznsDt.S.C6"),
                        new TestEntry("Z>BobP32ub\"Cfe*Q<<WUq3rc=[GJr-", 7, "hp8IdLueqE6qFh1zYycUZ.", "$2a$07$hp8IdLueqE6qFh1zYycUZ.twmUH8eSTPQAEpdNXKMlwms9XfKqfea"),
                        new TestEntry("Ik&8N['7*[1aCc1lOm8\\jWeD*H$eZM", 8, "2ANDTYCB9m7vf0Prh7rSru", "$2a$08$2ANDTYCB9m7vf0Prh7rSrupqpO3jJOkIz2oW/QHB4lCmK7qMytGV6"),
                        new TestEntry("O)=%3[E$*q+>-q-=tRSjOBh8\\mLNW.", 9, "nArqOfdCsD9kIbVnAixnwe", "$2a$09$nArqOfdCsD9kIbVnAixnwe6s8QvyPYWtQBpEXKir2OJF9/oNBsEFe"),
                        new TestEntry("/MH51`!BP&0tj3%YCA;Xk%e3S`o\\EI", 10, "ePiAc.s.yoBi3B6p1iQUCe", "$2a$10$ePiAc.s.yoBi3B6p1iQUCezn3mraLwpVJ5XGelVyYFKyp5FZn/y.u"),
                        new TestEntry("ptAP\"mcg6oH.\";c0U2_oll.OKi<!ku", 12, "aroG/pwwPj1tU5fl9a9pkO", "$2a$12$aroG/pwwPj1tU5fl9a9pkO4rydAmkXRj/LqfHZOSnR6LGAZ.z.jwa")
                });

        doPatrickFavTest(
                "Same Password and Random Salt",
                new TestEntry[]{
                        new TestEntry("Q/A:k3DP;X@=<0\"hg&9c", 4, "wbgDTvLMtyjQlNK7fjqwyO", "$2a$04$wbgDTvLMtyjQlNK7fjqwyOakBoACQuYh11.VsKNarF4xUIOBWgD6S"),
                        new TestEntry("Q/A:k3DP;X@=<0\"hg&9c", 5, "zbAaOmloOhxiKItjznRqru", "$2a$05$zbAaOmloOhxiKItjznRqrunRqHlu3MAa7pMGv26Rr3WwyfGcwoRm6"),
                        new TestEntry("Q/A:k3DP;X@=<0\"hg&9c", 6, "aOK0bWUvLI0qLkc3ti5jyu", "$2a$06$aOK0bWUvLI0qLkc3ti5jyuAIQoqRzuqoK09kQqQ6Ou/YKDhW50/qa")
                });

        doPatrickFavTest(
                "Same Password and Salt - increasing cost factor",
                new TestEntry[]{
                        new TestEntry("o<&+X'F4AQ8H,LU,N`&r", 4, "BK5u.QHk1Driey7bvnFTH.", "$2a$04$BK5u.QHk1Driey7bvnFTH.3smGwxd91PtoK2GxH5nZ7pcBsYX4lMq"),
                        new TestEntry("o<&+X'F4AQ8H,LU,N`&r", 5, "BK5u.QHk1Driey7bvnFTH.", "$2a$05$BK5u.QHk1Driey7bvnFTH.t5P.jZvFBMzDB1IY4PwkkRPOyVbEtFG"),
                        new TestEntry("o<&+X'F4AQ8H,LU,N`&r", 6, "BK5u.QHk1Driey7bvnFTH.", "$2a$06$BK5u.QHk1Driey7bvnFTH.6Ea1Z5db2p25CPXZbxb/3OyKQagg3pa"),
                        new TestEntry("o<&+X'F4AQ8H,LU,N`&r", 7, "BK5u.QHk1Driey7bvnFTH.", "$2a$07$BK5u.QHk1Driey7bvnFTH.sruuQi8Lhv/0LWKDvNp3AGFk7ltdkm6"),
                        new TestEntry("o<&+X'F4AQ8H,LU,N`&r", 8, "BK5u.QHk1Driey7bvnFTH.", "$2a$08$BK5u.QHk1Driey7bvnFTH.IE7KsaUzc4m7gzAMlyUPUeiYyACWe0q"),
                        new TestEntry("o<&+X'F4AQ8H,LU,N`&r", 9, "BK5u.QHk1Driey7bvnFTH.", "$2a$09$BK5u.QHk1Driey7bvnFTH.1v4Xj1dwkp44QNg0cVAoQt4FQMMrvnS"),
                        new TestEntry("o<&+X'F4AQ8H,LU,N`&r", 10, "BK5u.QHk1Driey7bvnFTH.", "$2a$10$BK5u.QHk1Driey7bvnFTH.ESINe9YntUMcVgFDfkC.Vbhc9vMhNX2"),
                        new TestEntry("o<&+X'F4AQ8H,LU,N`&r", 12, "BK5u.QHk1Driey7bvnFTH.", "$2a$12$BK5u.QHk1Driey7bvnFTH.QM1/nnGe/f5cTzb6XTTi/vMzcAnycqG")
                });

        doPatrickFavTest(
                "Long Passwords",
                new TestEntry[]{
                        new TestEntry("g*3Q45=\"8NNgpT&mbMJ$Omfr.#ZeW?FP=CE$#roHd?97uL0F-]`?u73c\"\\[.\"*)qU34@VG", 4, "T2XJ5MOWvHQZRijl8LIKkO", "$2a$04$T2XJ5MOWvHQZRijl8LIKkOQKIyX75KBfuLsuRYOJz5OjwBNF2lM8a"),
                        new TestEntry("\\M+*8;&QE=Ll[>5?Ui\"^ai#iQH7ZFtNMfs3AROnIncE9\"BNNoEgO[[*Yk8;RQ(#S,;I+aT", 5, "wgkOlGNXIVE2fWkT3gyRoO", "$2a$05$wgkOlGNXIVE2fWkT3gyRoOqWi4gbi1Wv2Q2Jx3xVs3apl1w.Wtj8C"),
                        new TestEntry("M.E1=dt<.L0Q&p;94NfGm_Oo23+Kpl@M5?WIAL.[@/:'S)W96G8N^AWb7_smmC]>7#fGoB", 6, "W9zTCl35nEvUukhhFzkKMe", "$2a$06$W9zTCl35nEvUukhhFzkKMekjT9/pj7M0lihRVEZrX3m8/SBNZRX7i")
                });

        doPatrickFavTest(
                "Increasing Password Length",
                new TestEntry[]{
                        new TestEntry("a", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.l4WvgHIVg17ZawDIrDM2IjlE64GDNQS"),
                        new TestEntry("aa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.AyUxBk.ThHlsLvRTH7IqcG7yVHJ3SXq"),
                        new TestEntry("aaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.BxOVac5xPB6XFdRc/ZrzM9FgZkqmvbW"),
                        new TestEntry("aaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.Qbr209bpCtfl5hN7UQlG/L4xiD3AKau"),
                        new TestEntry("aaaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.oWszihPjDZI0ypReKsaDOW1jBl7oOii"),
                        new TestEntry("aaaaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ./k.Xxn9YiqtV/sxh3EHbnOHd0Qsq27K"),
                        new TestEntry("aaaaaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.PYJqRFQbgRbIjMd5VNKmdKS4sBVOyDe"),
                        new TestEntry("aaaaaaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ..VMYfzaw1wP/SGxowpLeGf13fxCCt.q"),
                        new TestEntry("aaaaaaaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.5B0p054nO5WgAD1n04XslDY/bqY9RJi"),
                        new TestEntry("aaaaaaaaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.INBTgqm7sdlBJDg.J5mLMSRK25ri04y"),
                        new TestEntry("aaaaaaaaaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.s3y7CdFD0OR5p6rsZw/eZ.Dla40KLfm"),
                        new TestEntry("aaaaaaaaaaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.Jx742Djra6Q7PqJWnTAS.85c28g.Siq"),
                        new TestEntry("aaaaaaaaaaaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.oKMXW3EZcPHcUV0ib5vDBnh9HojXnLu"),
                        new TestEntry("aaaaaaaaaaaaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.w6nIjWpDPNSH5pZUvLjC1q25ONEQpeS"),
                        new TestEntry("aaaaaaaaaaaaaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.k1b2/r9A/hxdwKEKurg6OCn4MwMdiGq"),
                        new TestEntry("aaaaaaaaaaaaaaaa", 4, "5DCebwootqWMCp59ISrMJ.", "$2a$04$5DCebwootqWMCp59ISrMJ.3prCNHVX1Ws.7Hm2bJxFUnQOX9f7DFa")
                });

        doPatrickFavTest(
                "Non-ASCII Characters",
                new TestEntry[]{
                        new TestEntry("àèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝðÐ", 4, "D3qS2aoTVyqM7z8v8crLm.", "$2a$04$D3qS2aoTVyqM7z8v8crLm.3nKt4CzBZJbyFB.ZebmfCvRw7BGs.Xm"),
                        new TestEntry("àèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝðÐ", 5, "VA1FujiOCMPkUHQ8kF7IaO", "$2a$05$VA1FujiOCMPkUHQ8kF7IaOg7NGaNvpxwWzSluQutxEVmbZItRTsAa"),
                        new TestEntry("àèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝðÐ", 6, "TXiaNrPeBSz5ugiQlehRt.", "$2a$06$TXiaNrPeBSz5ugiQlehRt.gwpeDQnXWteQL4z2FulouBr6G7D9KUi"),
                        new TestEntry("âêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿ", 4, "YTn1Qlvps8e1odqMn6G5x.", "$2a$04$YTn1Qlvps8e1odqMn6G5x.85pqKql6w773EZJAExk7/BatYAI4tyO"),
                        new TestEntry("âêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿ", 5, "C.8k5vJKD2NtfrRI9o17DO", "$2a$05$C.8k5vJKD2NtfrRI9o17DOfIW0XnwItA529vJnh2jzYTb1QdoY0py"),
                        new TestEntry("âêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿ", 6, "xqfRPj3RYAgwurrhcA6uRO", "$2a$06$xqfRPj3RYAgwurrhcA6uROtGlXDp/U6/gkoDYHwlubtcVcNft5.vW"),
                        new TestEntry("ÄËÏÖÜŸåÅæÆœŒßçÇøØ¢¿¡€", 4, "y8vGgMmr9EdyxP9rmMKjH.", "$2a$04$y8vGgMmr9EdyxP9rmMKjH.wv2y3r7yRD79gykQtmb3N3zrwjKsyay"),
                        new TestEntry("ÄËÏÖÜŸåÅæÆœŒßçÇøØ¢¿¡€", 5, "iYH4XIKAOOm/xPQs7xKP1u", "$2a$05$iYH4XIKAOOm/xPQs7xKP1upD0cWyMn3Jf0ZWiizXbEkVpS41K1dcO"),
                        new TestEntry("ÄËÏÖÜŸåÅæÆœŒßçÇøØ¢¿¡€", 6, "wCOob.D0VV8twafNDB2ape", "$2a$06$wCOob.D0VV8twafNDB2apegiGD5nqF6Y1e6K95q6Y.R8C4QGd265q"),
                        new TestEntry("ΔημοσιεύθηκεστηνΕφημερίδατης", 4, "E5SQtS6P4568MDXW7cyUp.", "$2a$04$E5SQtS6P4568MDXW7cyUp.18wfDisKZBxifnPZjAI1d/KTYMfHPYO"),
                        new TestEntry("АБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмН", 4, "03e26gQFHhQwRNf81/ww9.", "$2a$04$03e26gQFHhQwRNf81/ww9.p1UbrNwxpzWjLuT.zpTLH4t/w5WhAhC"),
                        new TestEntry("нОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮю", 4, "PHNoJwpXCfe32nUtLv2Upu", "$2a$04$PHNoJwpXCfe32nUtLv2UpuhJXOzd4k7IdFwnEpYwfJVCZ/f/.8Pje"),
                        new TestEntry("電电電島岛島兔兔兎龜龟亀國国国區区区", 4, "wU4/0i1TmNl2u.1jIwBX.u", "$2a$04$wU4/0i1TmNl2u.1jIwBX.uZUaOL3Rc5ID7nlQRloQh6q5wwhV/zLW"),
                        new TestEntry("诶比伊艾弗豆贝尔维吾艾尺开艾丝维贼德", 4, "P4kreGLhCd26d4WIy7DJXu", "$2a$04$P4kreGLhCd26d4WIy7DJXusPkhxLvBouzV6OXkL5EB0jux0osjsry")
                });

        doPatrickFavTest(
                "Special Case Salts",
                new TestEntry[] {
                        // zero byte salts
                        new TestEntry("-O_=*N!2JP", 4, "......................", "$2a$04$......................JjuKLOX9OOwo5PceZZXSkaLDvdmgb82"),
                        new TestEntry("7B[$Q<4b>U", 5, "......................", "$2a$05$......................DRiedDQZRL3xq5A5FL8y7/6NM8a2Y5W"),
                        new TestEntry(">d5-I_8^.h", 6, "......................", "$2a$06$......................5Mq1Ng8jgDY.uHNU4h5p/x6BedzNH2W"),

                        // (byte) 1 array salts
                        new TestEntry(")V`/UM/]1t", 4, ".OC/.OC/.OC/.OC/.OC/.O", "$2a$04$.OC/.OC/.OC/.OC/.OC/.OQIvKRDAam.Hm5/IaV/.hc7P8gwwIbmi"),
                        new TestEntry(":@t2.bWuH]", 5, ".OC/.OC/.OC/.OC/.OC/.O", "$2a$05$.OC/.OC/.OC/.OC/.OC/.ONDbUvdOchUiKmQORX6BlkPofa/QxW9e"),
                        new TestEntry("b(#KljF5s\"", 6, ".OC/.OC/.OC/.OC/.OC/.O", "$2a$06$.OC/.OC/.OC/.OC/.OC/.OHfTd9e7svOu34vi1PCvOcAEq07ST7.K"),

                        // 0x80 bytes salt
                        new TestEntry("@3YaJ^Xs]*", 4, "eGA.eGA.eGA.eGA.eGA.e.", "$2a$04$eGA.eGA.eGA.eGA.eGA.e.stcmvh.R70m.0jbfSFVxlONdj1iws0C"),
                        new TestEntry("'\"5\\!k*C(p", 5, "eGA.eGA.eGA.eGA.eGA.e.", "$2a$05$eGA.eGA.eGA.eGA.eGA.e.vR37mVSbfdHwu.F0sNMvgn8oruQRghy"),
                        new TestEntry("edEu7C?$'W", 6, "eGA.eGA.eGA.eGA.eGA.e.", "$2a$06$eGA.eGA.eGA.eGA.eGA.e.tSq0FN8MWHQXJXNFnHTPQKtA.n2a..G"),

                        // 0xFF bytes salt
                        new TestEntry("N7dHmg\\PI^", 4, "999999999999999999999u", "$2a$04$999999999999999999999uCZfA/pLrlyngNDMq89r1uUk.bQ9icOu"),
                        new TestEntry("\"eJuHh!)7*", 5, "999999999999999999999u", "$2a$05$999999999999999999999uj8Pfx.ufrJFAoWFLjapYBS5vVEQQ/hK"),
                        new TestEntry("ZeDRJ:_tu:", 6, "999999999999999999999u", "$2a$06$999999999999999999999u6RB0P9UmbdbQgjoQFEJsrvrKe.BoU6q")
                });
    }
}
