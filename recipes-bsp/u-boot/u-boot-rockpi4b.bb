DESCRIPTION = "RockPi-4 U-Boot"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
COMPATIBLE_MACHINE = "(rk3399)"

require recipes-bsp/u-boot/u-boot.inc

SRCREV = "${AUTOREV}"
SRC_URI = " \
	git://github.com/radxa/u-boot.git;branch=stable-4.4-rockpi4 \
"
S = "${WORKDIR}/git"

do_configure () {
 	echo ${PWD}   
    oe_runmake -C ${S} O=${B} ${UBOOT_MACHINE}
}