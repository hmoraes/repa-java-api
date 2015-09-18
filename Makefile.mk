
################################################################################
###########################       JAVA VARIABLES     ###########################
################################################################################
# Verify if exist JNI includes and compiler
ifneq (${JAVA_PATH},)
# JNI_SRC this is the path of source base on repa-daemon project 
JNI_SRC := ../repa-java-api/src
# JNI_PKG is the JAVA API package where source code is 
JNI_PKG := ufrj.coppe.lcp.repa
JNI_PKG_EXP := ufrj.coppe.lcp.repa.experiment
JNI_LIBREPA := RepaSocket
JNI_LIBEXPERIMENTS := Experiments

librepa_hdr_JNI := $(JNI_PKG).$(JNI_LIBREPA)
exp_librepa_hdr_JNI := $(JNI_PKG_EXP).$(JNI_LIBEXPERIMENTS)
	
librepa_jar_SOURCES := \
	src/$(subst .,/,$(JNI_PKG))/RepaSocketException.java \
	src/$(subst .,/,$(JNI_PKG))/RepaConnectException.java \
	src/$(subst .,/,$(JNI_PKG))/PrefixAddress.java \
	src/$(subst .,/,$(JNI_PKG))/RepaSocket.java \
	src/$(subst .,/,$(JNI_PKG))/RepaMessage.java
	
exp_librepa_jar_SOURCES := \
	src/$(subst .,/,$(JNI_PKG))/experiment/Statistics.java \
	src/$(subst .,/,$(JNI_PKG))/experiment/Experiments.java

librepa_jar_CLASS := $(addprefix ${OBJS}/,$(subst ${SRC},${BIN},$(librepa_jar_SOURCES:.java=.class)))
exp_librepa_jar_CLASS := $(addprefix ${OBJS}/,$(subst ${SRC},${BIN},$(exp_librepa_jar_SOURCES:.java=.class)))

################################################################################
#########################       JAVA LIB-REPA RULES    #########################
################################################################################
# Create API to java (librepa.jar)
${OBJS}/${BIN}/%.class: $(JNI_SRC)/%.java 
	${QUIET} if [ -s "${JAVA_PATH}" ]; then \
		${ECHO} "* Compiling source \""$(notdir $<)"\""; \
		${MKDIR} ${OBJS}/${BIN}; \
		${JAVAC} -d ${OBJS}/${BIN} -cp ${OBJS}/${BIN} -sourcepath $(JNI_SRC) $<; \
	fi;

$(JNI_PKG).%: $(librepa_jar_CLASS) $(exp_librepa_jar_CLASS) 
	${QUIET} if [ -s "${JAVA_PATH}" ]; then \
		${ECHO} "- Genereting jni header for \"$@\"..."; \
		${JAVAH} -jni -d ${HDR}/lib -classpath ${OBJS}/${BIN}/ $@; \
	fi;

librepa.jar: $(librepa_jar_CLASS) $(exp_librepa_jar_CLASS) $(librepa_hdr_JNI) $(exp_librepa_hdr_JNI) 
	${QUIET} if [ -s "${JAVA_PATH}" ] && [ "$(JNI_SRC)" != "" ]; then \
		${ECHO} "+ Creating file \"$@\" file on \"$(shell basename ${CURDIR})/${APPS}/${ARCH}/\"..."; \
		${MKDIR} ${APPS}/${ARCH}; \
		${JAR} cf ${APPS}/${ARCH}/$@ -C ${OBJS}/${BIN}/ .; \
	fi;
endif