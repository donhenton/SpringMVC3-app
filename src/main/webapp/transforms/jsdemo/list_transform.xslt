<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output encoding="UTF-8" indent="yes" method="html"/>
    <xsl:template match="/">
        <ul>
        <xsl:apply-templates/>
        </ul>
    </xsl:template>
    <xsl:template match="//level3[@checked = 'yes']">
        <li>
        <label class="label label-important">
            <xsl:attribute name="for">
                <xsl:value-of select="concat('selected_item_', @id)" />
            </xsl:attribute>
            <i class="icon icon-small fi-price-tag"/>
            <xsl:value-of select="@name"/>
        </label>
        <input class="checkbox large" type="checkbox">
            <xsl:attribute name="id">
                <xsl:value-of select="concat('selected_item_', @id)" />
            </xsl:attribute>
            
            <xsl:if test="@checked = 'yes'">
                <xsl:attribute name="checked"/>
            </xsl:if>
            <xsl:attribute name="onclick">
                <xsl:value-of select="concat('XTree.selectItem(3,', @id,');')"/>
            </xsl:attribute>
        </input>
        </li>
    </xsl:template>
</xsl:stylesheet>
