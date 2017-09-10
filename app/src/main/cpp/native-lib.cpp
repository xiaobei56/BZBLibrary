#include <jni.h>
#include <string>

extern "C"
//JNIEXPORT jstring
//
//JNICALL
//Java_cn_gridlife_bzblibrary_MainActivity_stringFromJNI(
//        JNIEnv *env,
//        jobject /* this */) {
//    std::string hello = "Hello from C++0000";
//    return env->NewStringUTF(hello.c_str());
//}
//extern "C"

JNIEXPORT jstring JNICALL
Java_cn_gridlife_bzblibrary_jniCode_JniSample_stringFromJNI(JNIEnv *env, jclass type) {

    // TODO

    std::string hello = "Hello from C++0005959590";
    return env->NewStringUTF(hello.c_str());
}