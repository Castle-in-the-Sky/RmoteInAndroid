LOCAL_PATH := $(call my-dir)


include $(CLEAR_VARS)

LOCAL_MODULE    := IRDataBase
LOCAL_SRC_FILES :=  libIRDataBase.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := ETEKIRCore
LOCAL_SRC_FILES :=  libETEKIRCore.so
include $(PREBUILT_SHARED_LIBRARY)


include $(CLEAR_VARS)

LOCAL_MODULE    := MobileRemote

LOCAL_SHARED_LIBRARY := IRDataBase 	ETEKIRCore	
LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib -llog

include $(BUILD_SHARED_LIBRARY)