DESCRIPTION = "RockPi-4 U-Boot"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
COMPATIBLE_MACHINE = "(rk3399)"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "rk-binary-native"

UBOOT_BINARY = "uboot.img"
UBOOT_TEXT_BASE = "0x200000"



SRCREV = "${AUTOREV}"
SRC_URI = " \
	git://github.com/radxa/u-boot.git;branch=stable-4.4-rockpi4 \
"
S = "${WORKDIR}/git"

UBOOT_EXTLINUX = "1"
UBOOT_EXTLINUX_CONSOLE = "console=ttyS2,1500000n8"
UBOOT_EXTLINUX_LABELS = "linux"
UBOOT_EXTLINUX_FDT = "/rk3399-rock-pi-4.dtb"
UBOOT_EXTLINUX_KERNEL_IMAGE = "/${KERNEL_IMAGETYPE}"
UBOOT_EXTLINUX_ROOT = "root=/dev/mmcblk1p5 rootwait rootfstype=ext4"
UBOOT_EXTLINUX_KERNEL_ARGS = "earlyprintk rw init=/sbin/init"


UBOOT_EXTLINUX_CONFIG = "${B}/extlinux.conf"

do_configure () {
 	echo ${PWD}   
    oe_runmake -C ${S} O=${B} ${UBOOT_MACHINE}
}

do_compile_append () {
	loaderimage --pack --uboot ${B}/u-boot-dtb.bin ${B}/${UBOOT_BINARY} ${UBOOT_TEXT_BASE#*=} --size "${RK_LOADER_SIZE}" "${RK_LOADER_BACKUP_NUM}"
}

do_deploy_append () {
	bbnote "${PN}: Deploy ${UBOOT_BINARY} ...."
	install "${UBOOT_BINARY}" "${DEPLOYDIR}/${UBOOT_BINARY}-${SRCREV}"
	ln -sf "${UBOOT_BINARY}-${SRCREV}" "${DEPLOYDIR}/${UBOOT_BINARY}"
}
