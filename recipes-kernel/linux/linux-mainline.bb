DESCRIPTION = "Linux Mainline kernel for Rock Pi 4 B"

require recipes-kernel/linux/linux-yocto.inc

DEPENDS += "openssl-native"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRCREV = "${AUTOREV}"
SRC_URI = " \
	git://github.com/showliu/linux.git;branch=rkispv11 \
"

KERNEL_VERSION_SANITY_SKIP="1"
LINUX_VERSION = "5.4rc1"
LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}"

COMPATIBLE_MACHINE = "(rk3399)"

do_compile_append() {
	oe_runmake dtbs
}

do_deploy_append() {
	bbnote "${PN}: Deploy Kernel ${KERNEL_IMAGETYPE} ..."
#	install "${KERNEL_IMAGETYPE}" "${DEPLOYDIR}/${KERNEL_IMAGETYPE}-${SRCREV}"
#	ln -sf "${KERNEL_IMAGETYPE}-${SRCREV}" "${DEPLOYDIR}/${KERNEL_IMAGETYPE}"
}
deltask kernel_configme
