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

do_configure () {
 	echo ${PWD}   
    oe_runmake -C ${S} O=${B} ${UBOOT_MACHINE}
}

do_compile_append () {
	loaderimage --pack --uboot ${B}/u-boot.bin ${B}/${UBOOT_BINARY} ${UBOOT_TEXT_BASE#*=} --size "${RK_LOADER_SIZE}" "${RK_LOADER_BACKUP_NUM}"
}